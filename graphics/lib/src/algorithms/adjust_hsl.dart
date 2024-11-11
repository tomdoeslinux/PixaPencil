import 'package:graphics/src/algorithms/interpolate_colors.dart';

import '../core/bitmap.dart';
import '../core/bitmap_iterator.dart';
import '../core/color.dart';

GBitmap adjustHsl(
  GBitmap source,
  double h,
  double s,
  double l, {
  bool colorize = false,
}) {
  final sourceIterator = GBitmapIterator(source);

  final destBitmap = GBitmap(source.width, source.height, config: source.config);
  final destIterator = GBitmapIterator(destBitmap);

  final hsvComponents = List.filled(3, 0.0);

  do {
    final curPixel = sourceIterator.currentPixel;
    GColors.rgbToHsl(curPixel.r, curPixel.g, curPixel.b, hsvComponents);
    hsvComponents[0] = colorize ? h : (hsvComponents[0] + h) % 360;
    hsvComponents[1] = (hsvComponents[1] + s).clamp(0, 1);

    var newColor =
        GColors.hslToRgb(hsvComponents[0], hsvComponents[1], hsvComponents[2]);

    if (l < 0) {
      newColor = interpolateColors(newColor, GColors.black, l.abs());
    } else if (l > 0) {
      newColor = interpolateColors(newColor, GColors.white, l);
    }

    destIterator.put(newColor);
  } while (sourceIterator.moveHorizontal() && destIterator.moveHorizontal());

  return destBitmap;
}
