import 'package:graphics/graphics.dart';

abstract class Tool {
  final NodeGraph nodeGraph;

  Tool(this.nodeGraph);

  void onTouchDown(GPoint point, GColor color);
  void onTouchMove(GPoint point, GColor color);
  void onTouchUp();
}
