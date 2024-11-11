import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/color.dart';
import 'package:test/test.dart';

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
  });
}
