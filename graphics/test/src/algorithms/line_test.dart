import 'package:graphics_core/src/algorithms/line.dart';
import 'package:graphics_core/src/core/bitmap.dart';
import 'package:graphics_core/src/core/color.dart';
import 'package:graphics_core/src/core/point2d.dart';
import 'package:graphics_core/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  group("Line tests", () {
    Future<void> runLineTest(Point2D from, Point2D to) async {
      final width = (from.x > to.x ? from.x : to.x) + 1;
      final height = (from.y > to.y ? from.y : to.y) + 1;

      final bitmap = Bitmap(width, height, config: BitmapConfig.rgba);
      drawLine(bitmap, from, to, Colors.blue);

      final expectedFileName =
          "$testAssetPath/line_x${from.x}y${from.y}_x${to.x}y${to.y}.png";
      final expectedBitmap =
          await loadBitmapFromImage(expectedFileName, width, height);

      expect(bitmapsAreEqual(bitmap, expectedBitmap), isTrue);
    }

    test("line from (0, 0) to (23, 8) matches expected output", () {
      runLineTest((x: 0, y: 0), (x: 23, y: 8));
    });

    test("line from (54, 95) to (124, 421) matches expected output", () {
      runLineTest((x: 54, y: 95), (x: 124, y: 421));
    });

    test("line from (1221, 3321) to (5543, 6934) matches expected output", () {
      runLineTest((x: 1221, y: 3321), (x: 5543, y: 6934));
    });
  });
}
