import 'package:graphics/src/pipeline/node_graph.dart';
import 'package:graphics/src/core/point2d.dart';
import 'package:graphics/src/core/color.dart' as g;

abstract class Tool {
  final NodeGraph nodeGraph;

  Tool(this.nodeGraph);

  void onTouchDown(Point2D point, g.Color color);
  void onTouchMove(Point2D point, g.Color color);
  void onTouchUp();
}
