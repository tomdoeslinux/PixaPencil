import 'package:graphics_core/src/core/bitmap_iterator.dart';

import '../core/bitmap.dart';
import '../core/color.dart';

Bitmap adjustHsv(
  Bitmap source,
  double h,
  double s,
  double v,
) {
  final sourceIterator = BitmapIterator(source);

  final destBitmap = Bitmap(source.width, source.height, config: source.config);
  final destIterator = BitmapIterator(destBitmap);

  final hsvComponents = List.filled(3, 0.0);
  do {
    final curPixel = sourceIterator.currentPixel;
    Colors.rgbToHsv(curPixel.r, curPixel.g, curPixel.b, hsvComponents);
    hsvComponents[0] = (hsvComponents[0] + h) % 360;
    hsvComponents[1] = (hsvComponents[1] + s).clamp(0, 1);
    hsvComponents[2] = (hsvComponents[2] + v).clamp(0, 1);

    final newColor =
        Colors.hsvToRgb(hsvComponents[0], hsvComponents[1], hsvComponents[2]);
    destIterator.put(newColor);
  } while (sourceIterator.moveHorizontal() && destIterator.moveHorizontal());

  return destBitmap;
}
