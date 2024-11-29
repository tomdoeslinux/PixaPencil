import 'package:app/models/tool.dart';
import 'package:graphics/graphics.dart';

class PencilTool extends Tool {
  PathNode? _currentPathNode;
  final bool isEraser;

  PencilTool(super.drawingState, {this.isEraser = false});

  @override
  void onTouchDown(GPoint point) {
    _currentPathNode = PathNode(
      inputNode: null,
      path: [point],
      color: !isEraser ? drawingState.selectedColor : GColors.rgba(0, 0, 0, 0),
    );

    if (operatingNode.parentNode?.auxNode == operatingNode) {
      operatingNode.parentNode?.auxNode = _currentPathNode;
    } else {
      operatingNode.parentNode?.inputNode = _currentPathNode;
    }
    _currentPathNode?.inputNode = operatingNode;

    drawingState.nodeGraph.populateNodeLUT();
    drawingState.layerManager.populateLayers();
  }

  @override
  void onTouchMove(GPoint point) async {
    _currentPathNode?.addPoint(point);
  }

  @override
  void onTouchUp() {
    _currentPathNode = null;
  }
}
