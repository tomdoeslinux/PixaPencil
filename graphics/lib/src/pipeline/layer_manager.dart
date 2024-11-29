import 'graph_traversal.dart';
import 'node.dart';
import 'node_graph.dart';
import 'over_node.dart';

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
  var layers = <Layer>[];

  LayerManager(this.nodeGraph) {
    populateLayers();
  }

  void populateLayers() {
    layers.clear();

    final layerNodes = <Node>[];

    traverseGraphBFS(nodeGraph.rootNode, (node) {
      if (node is OverlayNode) {
        if (node.auxNode != null && node.auxNode is! OverlayNode) {
          layerNodes.add(node.auxNode!);
        }

        if (node.inputNode != null && node.inputNode is! OverlayNode) {
          layerNodes.add(node.inputNode!);
        }
      }
    });

    if (layerNodes.isEmpty) {
      final layer = Layer("Layer 0", nodeGraph.rootNode);
      layers.add(layer);

      return;
    }

    for (final (indx, node) in layerNodes.indexed) {
      final layer = Layer("Layer ${layerNodes.length - indx}", node);
      layers.insert(0, layer);
    }
  }

  void addLayer(Node newLayer, {int? position}) {
    final insertIndex = position ?? layers.length;

    if (insertIndex == layers.length) {
      nodeGraph.rootNode =
          OverlayNode(inputNode: nodeGraph.rootNode, auxNode: newLayer);
    } else {
      final existingLayer = layers[insertIndex];
      final node = existingLayer.rootNode;

      // we create copy to avoid circular reference
      final parent = node.parentNode;
      parent?.inputNode =
          OverlayNode(inputNode: parent.inputNode, auxNode: newLayer);
    }

    nodeGraph.populateNodeLUT();
    populateLayers();
  }

  void removeLayer(int layerIndex) {
    final layerNode = layers[layerIndex].rootNode;
    final parent = layerNode.parentNode;

    if (parent?.inputNode?.id == layerNode.id) {
      if (parent?.parentNode == null) {
        nodeGraph.rootNode = parent!.auxNode!;
        nodeGraph.rootNode.parentNode = null;
      } else {
        parent?.parentNode?.inputNode = parent.auxNode;
      }
    } else if (parent?.auxNode?.id == layerNode.id) {
      if (parent?.parentNode == null) {
        nodeGraph.rootNode = parent!.inputNode!;
        nodeGraph.rootNode.parentNode = null;
      } else {
        parent?.parentNode?.inputNode = parent.inputNode;
      }
    }

    nodeGraph.populateNodeLUT();
    populateLayers();
  }
}
