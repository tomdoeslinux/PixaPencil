import 'package:app/screens/drawing/drawing_state.dart';
import 'package:graphics/graphics.dart';

abstract class Tool {
  final DrawingState drawingState;

  Node get operatingNode => drawingState.layers[drawingState.activeLayerIndex].rootNode;

  Tool(this.drawingState);

  void onTouchDown(GPoint point);
  void onTouchMove(GPoint point);
  void onTouchUp();
}
