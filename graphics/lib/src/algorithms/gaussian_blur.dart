import 'dart:math';

import '../core/bitmap.dart';
import '../core/bitmap_iterator.dart';
import '../core/color.dart';

int _getGaussianKernelSize(double sigma) {
  final size = (sigma * 6.5).ceil();

  // Ensure value is odd
  return size + (1 - (size % 2));
}

double _gaussian(double sigma, int delta) {
  final fraction = 1 / sqrt(2 * pi * sigma * sigma);

  return fraction * exp(-1 * ((delta * delta) / (2 * (sigma * sigma))));
}

List<double> _getGaussianKernel(double sigma) {
  int kerSize = _getGaussianKernelSize(sigma);
  int centerIndex = kerSize ~/ 2;

  final kernel = List<double>.generate(kerSize, (i) {
    return _gaussian(sigma, i - centerIndex);
  });

  final kernelSum = kernel.reduce((acc, val) => acc + val);

  // ... the kernel is normalized to maintain image brightness
  return kernel.map((i) => i / kernelSum).toList();
}

GBitmap gaussianBlur(GBitmap source, double radius) {
  final width = source.width;
  final height = source.height;

  final sourceIterator = GBitmapIterator(source);

  final destBitmap = GBitmap(width, height, config: source.config);
  final destIterator = GBitmapIterator(destBitmap);

  // Calculate the gaussian kernel given the radius...
  final kernel = _getGaussianKernel(radius);

  // Horizontal pass
  do {
    var r = 0.0, g = 0.0, b = 0.0, a = 0.0;

    for (final (i, num) in kernel.indexed) {
      final newPixel = sourceIterator.getPixel(
          ((sourceIterator.x - (kernel.length ~/ 2)) + i).clamp(0, width - 1),
          sourceIterator.y);

      r += newPixel.r * num;
      g += newPixel.g * num;
      b += newPixel.b * num;
      a += newPixel.a * num;
    }

    final newR = r.round().clamp(0, 255);
    final newG = g.round().clamp(0, 255);
    final newB = b.round().clamp(0, 255);
    final newA = a.round().clamp(0, 255);

    destIterator.put(GColors.rgba(newR, newG, newB, newA));
  } while (sourceIterator.moveHorizontal() && destIterator.moveHorizontal());

  sourceIterator.reset();
  destIterator.reset();

  // Vertical pass
  do {
    var r = 0.0, g = 0.0, b = 0.0, a = 0.0;

    for (final (i, num) in kernel.indexed) {
      final newPixel = destIterator.getPixel(destIterator.x,
          ((destIterator.y - (kernel.length ~/ 2)) + i).clamp(0, height - 1));

      r += newPixel.r * num;
      g += newPixel.g * num;
      b += newPixel.b * num;
      a += newPixel.a * num;
    }

    final newR = r.round().clamp(0, 255);
    final newG = g.round().clamp(0, 255);
    final newB = b.round().clamp(0, 255);
    final newA = a.round().clamp(0, 255);

    destIterator.put(GColors.rgba(newR, newG, newB, newA));
  } while (sourceIterator.moveVertical() && destIterator.moveVertical());

  return destBitmap;
}
