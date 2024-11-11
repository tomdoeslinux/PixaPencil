import '../algorithms/gaussian_blur.dart';
import '../core/bitmap.dart';
import '../core/rect.dart';
import 'node.dart';

class BlurNode extends Node {
  final double radius;

  BlurNode({super.inputNode, required this.radius});

  @override
  GRect getRequiredROI(GRect outputRoi) {
    return outputRoi;
  }

  @override
  GBitmap operation(GRect? roi) {
    final inputBitmap = inputNode!.process(roi);

    return gaussianBlur(inputBitmap, radius);
  }
}