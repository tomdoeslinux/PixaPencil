import '../algorithms/line.dart';
import '../core/bitmap.dart';
import '../core/color.dart';
import '../core/point2d.dart';
import '../core/rect.dart';
import 'node.dart';

class PathNode extends Node {
  final List<Point2D> path;
  final Color color;

  PathNode({super.inputNode, required this.path, required this.color});

  void addPoint(Point2D point) {
    path.add(point);
  }

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap operation(Rect? roi) {
    final inputBitmap = inputNode!.process(roi);

    Point2D? prevPoint;

    for (final point in path) {
      drawLine(inputBitmap, prevPoint ?? point, point, color);
      prevPoint = point;
    }

    return inputBitmap;
  }
}
