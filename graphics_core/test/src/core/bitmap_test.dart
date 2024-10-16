import 'package:graphics_core/src/core/bitmap.dart';
import 'package:graphics_core/src/core/color.dart';
import 'package:test/test.dart';

void main() {
  group("Bitmap tests", () {
    test("setting and getting a pixel", () {
      final bitmap = Bitmap(5, 5, config: BitmapConfig.rgba);

      bitmap.setPixel(0, 0, Colors.red);

      expect(bitmap.getPixel(0, 0), equals(Colors.red));
      expect(bitmap.getPixel(0, 1), equals(Colors.rgba(0, 0, 0, 0)));
    });

    test("accessing pixel out of bounds should throw ex", () {
      final bitmap = Bitmap(10, 10, config: BitmapConfig.rgba);

      expect(() => bitmap.getPixel(11, 11), throwsRangeError);
      expect(() => bitmap.getPixel(11, 0), throwsRangeError);
      expect(() => bitmap.getPixel(0, 11), throwsRangeError);
      expect(() => bitmap.getPixel(-3, -6), throwsRangeError);
    });

    test("bitmap gets filled properly", () {
      final bitmap = Bitmap(5, 5, config: BitmapConfig.rgba);
      bitmap.fillColor(Colors.blue);

      for (var x = 0; x < bitmap.width; ++x) {
        for (var y = 0; y < bitmap.height; ++y) {
          expect(bitmap.getPixel(x, y), equals(Colors.blue));
        }
      }
    });
  });
}
