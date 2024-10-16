import 'package:graphics_core/src/core/bitmap.dart';

const testAssetPath = "test/assets";

bool bitmapsAreEqual(Bitmap a, Bitmap b) {
  if (a.width != b.width || a.height != b.height) {
    return false;
  }

  for (var x = 0; x < a.width; ++x) {
    for (var y = 0; y < a.height; ++y) {
      if (a.getPixel(x, y) != b.getPixel(x, y)) {
        return false;
      }
    }
  }

  return true;
}