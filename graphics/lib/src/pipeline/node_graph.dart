import '../core/bitmap.dart';
import '../core/rect.dart';
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

  Bitmap process(Rect outputRoi) {
    final requiredRoi = rootNode.getRequiredROI(outputRoi);

    return rootNode.process(requiredRoi);
  }
}