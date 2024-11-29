import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/region.dart';

import 'graph_traversal.dart';
import 'node.dart';

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

  GBitmap process(GRegion outputRoi) {
    return rootNode.process(outputRoi);
  }
}