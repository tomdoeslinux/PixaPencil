import 'package:graphics_core/src/algorithms/gaussian_blur.dart';
import 'package:graphics_core/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  group("Gaussian blur tests", () {
    Future<void> runGaussianBlurTest({required double radius}) async {
      const width = 1280;
      const height = 851;

      final bitmap = await loadBitmapFromImage(
          "$testAssetPath/dummy_image.jpg", width, height);
      final blurredBitmap = gaussianBlur(bitmap, radius);

      final expectedFileName = "dummy_image_blur_$radius.jpg";
      final expectedBitmap =
          await loadBitmapFromImage(expectedFileName, width, height);

      expect(bitmapsAreEqual(blurredBitmap, expectedBitmap), isTrue);
    }

    test("blur radius 5 matches expected output", () {
      runGaussianBlurTest(radius: 5);
    });

    test("blur radius 20 matches expected output", () {
      runGaussianBlurTest(radius: 20);
    });

    test("blur radius 50 matches expected output", () {
      runGaussianBlurTest(radius: 50);
    });
  });
}
