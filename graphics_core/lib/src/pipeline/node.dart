import "dart:collection";
import "dart:typed_data";

import "package:graphics_core/src/algorithms/blend_colors.dart";
import "package:graphics_core/src/algorithms/gaussian_blur.dart";
import "package:graphics_core/src/algorithms/line.dart";
import "package:graphics_core/src/core/color.dart";
import "package:graphics_core/src/core/point2d.dart";

import "../core/bitmap.dart";
import "../core/rect.dart";
import "../utils.dart";

abstract class Node {
  static int _idCounter = 0;
  final int id;
  Node? inputNode;
  Node? parentNode;
  Node? auxNode;

  Node({required this.inputNode, this.auxNode}) : id = _generateId() {
    inputNode?.parentNode = this;
    auxNode?.parentNode = this;
  }

  static int _generateId() {
    return _idCounter++;
  }

  // Calculates the region of the source image that is necessary to process a
  // ...specific Region of Interest (ROI) in the output.
  Rect getRequiredROI(Rect outputRoi);

  Bitmap process(Rect? roi);
}

class BlurNode extends Node {
  final double radius;

  BlurNode({super.inputNode, required this.radius});

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap process(Rect? roi) {
    final inputBitmap = inputNode!.process(roi);

    return gaussianBlur(inputBitmap, radius);
  }
}

class SourceNode extends Node {
  final Bitmap source;

  SourceNode({required this.source}) : super(inputNode: null);

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap process(Rect? roi) {
    return roi != null ? source.crop(roi) : source;
  }
}

class PathNode extends Node {
  final List<Point2D> path;

  PathNode({super.inputNode, required this.path});

  void addPoint(Point2D point) {
    path.add(point);
  }

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap process(Rect? roi) {
    final inputBitmap = inputNode!.process(roi);

    Point2D? prevPoint;

    for (final point in path) {
      drawLine(inputBitmap, prevPoint ?? point, point, Colors.black);
      prevPoint = point;
    }

    return inputBitmap;
  }
}

class OverNode extends Node {
  OverNode({super.inputNode, super.auxNode});

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap process(Rect? roi) {
    final baseBitmap = inputNode!.process(roi);
    final overlayBitmap = auxNode!.process(null);

    final numChannels = overlayBitmap.config.numChannels;

    final rowLength = overlayBitmap.width * numChannels;
    final blendedRow = Uint8List(rowLength);

    for (var y = 0; y < overlayBitmap.height; ++y) {
      final baseRowStartIndex = y * baseBitmap.width * numChannels;
      final overlayRowStartIndex = y * overlayBitmap.width * numChannels;

      for (var x = 0; x < overlayBitmap.width; ++x) {
        final basePixelIndex = baseRowStartIndex + x * numChannels;
        final overlayPixelIndex = overlayRowStartIndex + x * numChannels;

        final baseColor = Colors.rgba(
          baseBitmap.pixels[basePixelIndex],
          baseBitmap.pixels[basePixelIndex + 1],
          baseBitmap.pixels[basePixelIndex + 2],
          baseBitmap.pixels[basePixelIndex + 3],
        );

        final overlayColor = Colors.rgba(
          overlayBitmap.pixels[overlayPixelIndex],
          overlayBitmap.pixels[overlayPixelIndex + 1],
          overlayBitmap.pixels[overlayPixelIndex + 2],
          overlayBitmap.pixels[overlayPixelIndex + 3],
        );

        final blendedColor = blendColors(overlayColor, baseColor);

        blendedRow[x * numChannels] = blendedColor.r;
        blendedRow[x * numChannels + 1] = blendedColor.g;
        blendedRow[x * numChannels + 2] = blendedColor.b;
        blendedRow[x * numChannels + 3] = blendedColor.a;
      }

      baseBitmap.pixels.setRange(
        baseRowStartIndex,
        baseRowStartIndex + rowLength,
        blendedRow,
      );
    }

    return baseBitmap;
  }
}

class NodeGraph {
  Node rootNode;
  final Map<int, Node> nodeLUT = {};

  NodeGraph(this.rootNode) {
    populateNodeLUT();
  }

  void populateNodeLUT() {
    traverseGraphBFS(rootNode, (node) {
      nodeLUT[node.id] = node;
    });
  }

  Bitmap process(Rect outputRoi) {
    final requiredRoi = rootNode.getRequiredROI(outputRoi);

    return rootNode.process(requiredRoi);
  }
}

void traverseGraphBFS(Node rootNode, void Function(Node) onNodeVisit) {
  final queue = Queue.of([rootNode]);

  while (queue.isNotEmpty) {
    final currentNode = queue.removeFirst();

    onNodeVisit(currentNode);

    if (currentNode.inputNode != null) {
      queue.add(currentNode.inputNode!);
    }

    if (currentNode.auxNode != null) {
      queue.add(currentNode.auxNode!);
    }
  }
}

class Layer {
  final String name;
  final Node rootNode;

  const Layer(this.name, this.rootNode);

  @override
  String toString() {
    return 'Layer(name: "$name", rootNodeId: ${rootNode.id})';
  }
}

class LayerManager {
  final NodeGraph nodeGraph;
  List<Layer> layers = [];

  LayerManager(this.nodeGraph) {
    _populateLayers();
  }

  void _populateLayers() {
    layers.clear();

    final layerNodes = <Node>[];

    traverseGraphBFS(nodeGraph.rootNode, (node) {
      if (node is OverNode) {
        if (node.auxNode != null && node.auxNode is! OverNode) {
          layerNodes.add(node.auxNode!);
        }

        if (node.inputNode != null && node.inputNode is! OverNode) {
          layerNodes.add(node.inputNode!);
        }
      }
    });

    for (final (indx, node) in layerNodes.indexed) {
      final layer = Layer("Layer ${layerNodes.length - indx}", node);
      layers.insert(0, layer);
    }
  }

  void addLayer(Node newLayer, {int? position}) {
    final insertIndex = position ?? layers.length;

    if (insertIndex == layers.length) {
      nodeGraph.rootNode =
          OverNode(inputNode: nodeGraph.rootNode, auxNode: newLayer);
    } else {
      final existingLayer = layers[insertIndex];
      final node = existingLayer.rootNode;

      // we create copy to avoid circular reference
      final parent = node.parentNode;
      parent?.inputNode =
          OverNode(inputNode: parent.inputNode, auxNode: newLayer);
    }

    nodeGraph.populateNodeLUT();
    _populateLayers();
  }

  void removeLayer(int targetNodeId) {}
}

void test1() async {
  final srcBitmap = await loadBitmapFromImage("mario.png", 382, 853);
  final overBitmap = await loadBitmapFromImage("pizza.png", 71, 46);

  final nodeGraph = NodeGraph(
    OverNode(
      auxNode: SourceNode(source: overBitmap),
      inputNode: BlurNode(
        radius: 15,
        inputNode: SourceNode(source: srcBitmap),
      ),
    ),
  );

  final layer = LayerManager(nodeGraph);

  layer.addLayer(
    SourceNode(
      source: Bitmap(900, 900, config: BitmapConfig.rgba)
        ..fillColor(Colors.rgba(0, 0, 255, 255)),
    ),
  );

  await exportGraphToPNG(nodeGraph.rootNode, "visualization4");

  final outputBitmap =
      nodeGraph.process(Rect(x: 0, y: 0, width: 382, height: 853));

  saveBitmapToLocalDir(
    outputBitmap,
    "lol",
  );

  saveBitmapToLocalDir(
    Bitmap(100, 100, config: BitmapConfig.rgba)
      ..fillColor(Colors.rgba(255, 0, 0, 255)),
    "filled",
  );
}

void main() async {
  final nodeGraph = NodeGraph(
    OverNode(
      inputNode: SourceNode(
        source: Bitmap(100, 100, config: BitmapConfig.rgba)
          ..fillColor(Colors.rgba(255, 0, 0, 255)),
      ),
      auxNode: SourceNode(
        source: Bitmap(50, 50, config: BitmapConfig.rgba)
          ..fillColor(Colors.rgba(0, 255, 255, 220)),
      ),
    ),
  );

  final layer = LayerManager(nodeGraph);

  // add layer
  layer.addLayer(
    SourceNode(
      source: Bitmap(30, 60, config: BitmapConfig.rgba)
        ..fillColor(Colors.green),
    ),
    position: 1,
  );

  layer.addLayer(
    SourceNode(
      source: Bitmap(65, 65, config: BitmapConfig.rgba)
        ..fillColor(Colors.black),
    ),
    position: 1,
  );

  await exportGraphToPNG(nodeGraph.rootNode, "jjnat");

  final outputBitmap =
      nodeGraph.process(Rect(x: 0, y: 0, width: 100, height: 100));

  saveBitmapToLocalDir(
    outputBitmap,
    "demo.png",
  );
}
