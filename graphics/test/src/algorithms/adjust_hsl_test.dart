import 'package:graphics_core/src/algorithms/adjust_hsl.dart';
import 'package:graphics_core/src/core/bitmap.dart';
import 'package:graphics_core/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  late Bitmap eiffel;

  setUpAll(() async {
    eiffel = await loadBitmapFromImage("$testAssetPath/eiffel.png");
  });

  group("Adjust HSL tests", () {
    test("hue -92% adjusts properly", () async {
      final adjusted = adjustHsl(eiffel, -0.92, 0, 0);
      final expected = await loadBitmapFromImage("$testAssetPath/eiffel_hue_minus_92.png");

      expect(bitmapsAreEqual(adjusted, expected), isTrue);
    });

    test("sat +86% adjusts properly", () async {
      final adjusted = adjustHsl(eiffel, 0, 0.86, 0);
      final expected = await loadBitmapFromImage("$testAssetPath/eiffel_sat_plus_86.png");

      expect(bitmapsAreEqual(adjusted, expected), isTrue);
    });
  });
}
