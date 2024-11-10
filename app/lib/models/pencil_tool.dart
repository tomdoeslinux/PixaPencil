import 'package:app/models/tool.dart';
import 'package:graphics/src/core/point2d.dart';
import 'package:graphics/src/pipeline/path_node.dart';
import 'package:graphics/src/core/color.dart' as g;

class PencilTool extends Tool {
  PathNode? _currentPathNode;

  PencilTool(super.nodeGraph);

  @override
  void onTouchDown(Point2D point, g.Color color) {
    _currentPathNode = PathNode(inputNode: nodeGraph.rootNode, path: [point], color: color);
    nodeGraph.rootNode = _currentPathNode!;
  }

  @override
  void onTouchMove(Point2D point, g.Color color) {
    _currentPathNode?.addPoint(point);
  }

  @override
  void onTouchUp() {
    _currentPathNode = null;
  }
}
