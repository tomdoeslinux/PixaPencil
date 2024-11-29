import 'package:graphics/src/core/region.dart';

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
  GBitmap operation(GRegion roi) {
    final inputBitmap = inputNode!.process(roi);

    GPoint? prevPoint;

    for (final point in path) {
      drawLine(inputBitmap, prevPoint ?? point, point, color);
      prevPoint = point;
    }

    return inputBitmap;
  }

  @override
  GRect get boundingBox {
    var minX = path[0].x;
    var minY = path[0].y;

    var maxX = path[0].x;
    var maxY = path[0].y;

    for (final point in path) {
      if (point.x < minX) minX = point.x;
      if (point.y < minY) minY = point.y;

      if (point.x > maxX) maxX = point.x;
      if (point.y > maxY) maxY = point.y;
    }

    return GRect(
      x: minX,
      y: minY,
      width: maxX - minX,
      height: maxY - minY,
    );
  }
}
