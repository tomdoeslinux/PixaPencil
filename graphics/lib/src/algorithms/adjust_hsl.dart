import 'package:graphics_core/src/algorithms/interpolate_colors.dart';

import '../core/bitmap.dart';
import '../core/bitmap_iterator.dart';
import '../core/color.dart';

Bitmap adjustHsl(
  Bitmap source,
  double h,
  double s,
  double l,
) {
  final sourceIterator = BitmapIterator(source);

  final destBitmap = Bitmap(source.width, source.height, config: source.config);
  final destIterator = BitmapIterator(destBitmap);

  final hsvComponents = List.filled(3, 0.0);
  do {
    final curPixel = sourceIterator.currentPixel;
    Colors.rgbToHsl(curPixel.r, curPixel.g, curPixel.b, hsvComponents);
    hsvComponents[0] = (hsvComponents[0] + h) % 360;
    hsvComponents[1] = (hsvComponents[1] + s).clamp(0, 1);

    var newColor =
        Colors.hslToRgb(hsvComponents[0], hsvComponents[1], hsvComponents[2]);
    
    if (l < 0) {
      newColor = interpolateColors(newColor, Colors.black, l.abs());
    } else if (l > 0) {
      newColor = interpolateColors(newColor, Colors.white, l);
    }

    destIterator.put(newColor);
  } while (sourceIterator.moveHorizontal() && destIterator.moveHorizontal());

  return destBitmap;
}
