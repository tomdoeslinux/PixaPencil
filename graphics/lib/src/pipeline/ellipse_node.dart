import 'package:graphics/src/algorithms/ellipse.dart';
import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/color.dart';
import 'package:graphics/src/core/point.dart';
import 'package:graphics/src/core/rect.dart';
import 'package:graphics/src/core/region.dart';
import 'package:graphics/src/pipeline/node.dart';

class EllipseNode extends Node {
  final GPoint from;
  final GPoint to;
  final GColor color;

  EllipseNode({
    required super.inputNode,
    required this.from,
    required this.to,
    required this.color,
  });

  @override
  GBitmap operation(GRegion roi) {
    final inputBitmap = inputNode!.process(roi);

    drawEllipse(
      bitmap: inputBitmap,
      from: from,
      to: to,
      fillColor: color,
      strokeColor: color,
      strokeThickness: 0,
    );

    return inputBitmap;
  }

  @override
  GRect get boundingBox => GRect.between(from, to);
}
