import 'package:graphics/graphics.dart';
import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/rect.dart';
import 'package:graphics/src/pipeline/ellipse_node.dart';
import 'package:graphics/src/pipeline/layer_manager.dart';
import 'package:graphics/src/pipeline/node_graph.dart';
import 'package:graphics/src/pipeline/source_node.dart';
import 'package:graphics/src/utils.dart';

import '../../utils.dart';

void main() async {
  final nodeGraph = NodeGraph(
    SourceNode(
      source: GBitmap(500, 500, config: GBitmapConfig.rgba)..fillColor(GColors.green),
    ),
  );

  final layerManager = LayerManager(nodeGraph);
  layerManager.addLayer(
    EllipseNode(
      from: (x: 0, y: 0),
      to: (x: 300, y: 499),
      color: GColors.red,
      inputNode: SourceNode(
        source: GBitmap(500, 500, config: GBitmapConfig.rgba),
      ),
    ),
  );
  layerManager.addLayer(
    BlurNode(
      radius: 1,
      inputNode: PathNode(
        color: GColors.red,
        path: [
          (x: 0, y: 0),
          (x: 55, y: 95),
          (x: 399, y: 34),
          (x: 0, y: 499),
          (x: 200, y: 200),
          (x: 499, y: 499),
        ],
        inputNode: SourceNode(
          source: GBitmap(500, 500, config: GBitmapConfig.rgba),
        ),
      ),
    ),
  );

  saveBitmapToLocalDir(nodeGraph.process(GRect(x: 0, y: 0, width: 500, height: 500)), "output.png");
  exportGraphToPNG(nodeGraph.rootNode, "output_graph");

  // benchmark(() {
  //   nodeGraph.process(GRect(x: 0, y: 0, width: 500, height: 500));
  // }, iterations: 1);
}
