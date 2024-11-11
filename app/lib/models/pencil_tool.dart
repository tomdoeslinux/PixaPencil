import 'package:app/models/tool.dart';
import 'package:graphics/graphics.dart';

class PencilTool extends Tool {
  PathNode? _currentPathNode;

  PencilTool(super.nodeGraph);

  @override
  void onTouchDown(GPoint point, GColor color) {
    _currentPathNode = PathNode(inputNode: nodeGraph.rootNode, path: [point], color: color);
    nodeGraph.rootNode = _currentPathNode!;
  }

  @override
  void onTouchMove(GPoint point, GColor color) {
    _currentPathNode?.addPoint(point);
  }

  @override
  void onTouchUp() {
    _currentPathNode = null;
  }
}
