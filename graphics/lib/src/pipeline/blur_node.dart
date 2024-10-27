import '../algorithms/gaussian_blur.dart';
import '../core/bitmap.dart';
import '../core/rect.dart';
import 'node.dart';

class BlurNode extends Node {
  final double radius;

  BlurNode({super.inputNode, required this.radius});

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap operation(Rect? roi) {
    final inputBitmap = inputNode!.process(roi);

    return gaussianBlur(inputBitmap, radius);
  }
}