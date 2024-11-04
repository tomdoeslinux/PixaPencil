import 'package:graphics/src/algorithms/gaussian_blur.dart';
import 'package:graphics/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  group("Gaussian blur tests", () {
    Future<void> runGaussianBlurTest({required double radius}) async {
      final bitmap = await loadBitmapFromImage(
          "$testAssetPath/dummy_image.jpg");
      final blurredBitmap = gaussianBlur(bitmap, radius);

      final expectedFileName = "dummy_image_blur_$radius.jpg";
      final expectedBitmap =
          await loadBitmapFromImage(expectedFileName);

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
