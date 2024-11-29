import 'package:graphics/src/algorithms/ellipse.dart';
import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/color.dart';
import 'package:graphics/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  group("Ellipse tests", () {
    test("ellipse from (0, 0) to (49, 49) matches expected output", () async {
      final bitmap = GBitmap(50, 50, config: GBitmapConfig.rgba);

      drawEllipse(
        bitmap: bitmap,
        from: (x: 0, y: 0),
        to: (x: 49, y: 49),
        fillColor: GColors.blue,
        strokeColor: GColors.blue,
        strokeThickness: 0,
      );

      expect(
        bitmapsAreEqual(
          bitmap,
          await loadBitmapFromImage("$testAssetPath/ellipse_x0y0_x49y49.png"),
        ),
        isTrue,
      );
    });

    test("ellipse from (30, 30) to (199, 89) matches expected output",
        () async {
      final bitmap = GBitmap(200, 90, config: GBitmapConfig.rgba);

      drawEllipse(
        bitmap: bitmap,
        from: (x: 30, y: 30),
        to: (x: 199, y: 89),
        fillColor: GColors.blue,
        strokeColor: GColors.blue,
        strokeThickness: 0,
      );

      expect(
        bitmapsAreEqual(
          bitmap,
          await loadBitmapFromImage(
              "$testAssetPath/ellipse_x30y30_x199y89.png"),
        ),
        isTrue,
      );
    });

    test("ellipse from (0, 0) to (1, 1) matches expected output", () async {
      final bitmap = GBitmap(2, 2, config: GBitmapConfig.rgba);

      drawEllipse(
        bitmap: bitmap,
        from: (x: 0, y: 0),
        to: (x: 1, y: 1),
        fillColor: GColors.blue,
        strokeColor: GColors.blue,
        strokeThickness: 0,
      );

      expect(
        bitmapsAreEqual(
          bitmap,
          await loadBitmapFromImage(
              "$testAssetPath/ellipse_x0y0_x1y1.png"),
        ),
        isTrue,
      );
    });
  });
}
