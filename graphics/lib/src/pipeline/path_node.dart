import '../algorithms/line.dart';
import '../core/bitmap.dart';
import '../core/color.dart';
import '../core/point.dart';
import '../core/rect.dart';
import 'node.dart';

class PathNode extends Node {
  final List<GPoint> path;
  final GColor color;

  PathNode({super.inputNode, required this.path, required this.color});

  void addPoint(GPoint point) {
    path.add(point);
  }

  @override
  GRect getRequiredROI(GRect outputRoi) {
    return outputRoi;
  }

  @override
  GBitmap operation(GRect? roi) {
    final inputBitmap = inputNode!.process(roi);

    GPoint? prevPoint;

    for (final point in path) {
      drawLine(inputBitmap, prevPoint ?? point, point, color);
      prevPoint = point;
    }

    return inputBitmap;
  }
}
