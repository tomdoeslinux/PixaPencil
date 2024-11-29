import 'package:graphics/src/core/region.dart';

import '../algorithms/gaussian_blur.dart';
import '../core/bitmap.dart';
import '../core/rect.dart';
import 'node.dart';

class BlurNode extends Node {
  final double radius;

  BlurNode({super.inputNode, required this.radius});

  @override
  GBitmap operation(GRegion roi) {
    final inputBitmap = inputNode!.process(roi);

    return gaussianBlur(inputBitmap, radius);
  }

  @override
  GRect get boundingBox => inputNode!.boundingBox;
}