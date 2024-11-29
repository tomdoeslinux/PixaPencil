import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/color.dart';
import 'package:graphics/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  group("Bitmap tests", () {
    test("setting and getting a pixel", () {
      final bitmap = GBitmap(5, 5, config: GBitmapConfig.rgba);

      bitmap.setPixel(0, 0, GColors.red);

      expect(bitmap.getPixel(0, 0), equals(GColors.red));
      expect(bitmap.getPixel(0, 1), equals(GColors.rgba(0, 0, 0, 0)));
    });

    test("accessing pixel out of bounds should throw ex", () {
      final bitmap = GBitmap(10, 10, config: GBitmapConfig.rgba);

      expect(() => bitmap.getPixel(11, 11), throwsRangeError);
      expect(() => bitmap.getPixel(11, 0), throwsRangeError);
      expect(() => bitmap.getPixel(0, 11), throwsRangeError);
      expect(() => bitmap.getPixel(-3, -6), throwsRangeError);
    });

    test("bitmap gets filled properly", () {
      final bitmap = GBitmap(5, 5, config: GBitmapConfig.rgba);
      bitmap.fillColor(GColors.blue);

      for (var x = 0; x < bitmap.width; ++x) {
        for (var y = 0; y < bitmap.height; ++y) {
          expect(bitmap.getPixel(x, y), equals(GColors.blue));
        }
      }
    });

    test("overlay works as expected", () async {
      final baseBitmap =
          await loadBitmapFromImage("$testAssetPath/base_bitmap.jpg");
      final overlayBitmap =
          await loadBitmapFromImage("$testAssetPath/overlay_bitmap.png");

      final overlayedBitmap = GBitmap.overlay(baseBitmap, overlayBitmap);
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/overlayed_bitmap.png");

      expect(bitmapsAreEqual(overlayedBitmap, expectedBitmap), isTrue);
    });

    test("overlay works as expected with startX 215, startY 93", () async {
      final baseBitmap =
          await loadBitmapFromImage("$testAssetPath/base_bitmap.jpg");
      final overlayBitmap =
          await loadBitmapFromImage("$testAssetPath/overlay_bitmap.png");

      final overlayedBitmap =
          GBitmap.overlay(baseBitmap, overlayBitmap, startX: 215, startY: 93);
      final expectedBitmap = await loadBitmapFromImage(
          "$testAssetPath/overlayed_bitmap_offset_x215_y93.png");

      expect(bitmapsAreEqual(overlayedBitmap, expectedBitmap), isTrue);
    });

    test("overlay works as expected with startX -137, startY -132", () async {
      final baseBitmap =
          await loadBitmapFromImage("$testAssetPath/base_bitmap.jpg");
      final overlayBitmap =
          await loadBitmapFromImage("$testAssetPath/overlay_bitmap.png");

      final overlayedBitmap =
          GBitmap.overlay(baseBitmap, overlayBitmap, startX: 137, startY: -132);
      final expectedBitmap = await loadBitmapFromImage(
          "$testAssetPath/overlayed_bitmap_offset_x137_y-132.png");

      expect(bitmapsAreEqual(overlayedBitmap, expectedBitmap), isTrue);
    });

    test("overlay works as expected with startX -312, startY -192", () async {
      final baseBitmap =
          await loadBitmapFromImage("$testAssetPath/base_bitmap.jpg");
      final overlayBitmap =
          await loadBitmapFromImage("$testAssetPath/overlay_bitmap.png");

      final overlayedBitmap = GBitmap.overlay(baseBitmap, overlayBitmap,
          startX: -312, startY: -192);
      final expectedBitmap = await loadBitmapFromImage(
          "$testAssetPath/overlayed_bitmap_offset_x-312_y-192.png");

      expect(bitmapsAreEqual(overlayedBitmap, expectedBitmap), isTrue);
    });
  });
}
