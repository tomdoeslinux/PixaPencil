import '../core/bitmap.dart';
import '../core/bitmap_iterator.dart';
import '../core/color.dart';
import '../core/point.dart';
import '../core/vector.dart';
import 'interpolate_colors.dart';

void drawLinearGradient(
  GBitmap source,
  GPoint from,
  GPoint to,
  GColor fromColor,
  GColor toColor,
) {
  if (fromColor == toColor) {
    // clear
  }

  final GVector gradientStart = (from.x.toDouble(), from.y.toDouble());
  final GVector gradientEnd = (to.x.toDouble(), to.y.toDouble());

  final gradientLine = gradientEnd.subtract(gradientStart);
  final gradientLineMagnitude = gradientLine.magnitude();

  final gradientLineNorm = gradientLine.normalize();

  if (fromColor.a == 0 && toColor.a != 0) {
    fromColor = GColors.rgba(fromColor.r, fromColor.g, fromColor.b, 0);
  } else if (fromColor.a != 0 && toColor.a == 0) {
    toColor = GColors.rgba(toColor.r, toColor.g, toColor.b, 0);
  }

  final sourceIterator = GBitmapIterator(source);

  // This lookup table is crucial for performance
  const steps = 256;
  final colorLUT = List<int>.generate(steps, (i) {
    final double factor = i / (steps - 1);
    return interpolateColors(fromColor, toColor, factor);
  });

  GColor colorToSet;

  do {
    GVector curPixel =
        (sourceIterator.x.toDouble(), sourceIterator.y.toDouble());
    curPixel = curPixel.subtract(gradientStart);

    final projFactor = curPixel.dot(gradientLineNorm) / gradientLineMagnitude;

    if (projFactor > 1) {
      colorToSet = toColor;
    } else if (projFactor < 0) {
      colorToSet = fromColor;
    } else {
      final lutIndex = (projFactor * (steps - 1)).clamp(0, steps - 1).toInt();
      colorToSet = colorLUT[lutIndex];
    }

    sourceIterator.put(colorToSet);
  } while (sourceIterator.moveNext());
}
