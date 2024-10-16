import '../core/bitmap.dart';
import '../core/color.dart';
import '../core/point2d.dart';

void _drawLineY(
  Bitmap bitmap,
  Point2D from,
  Point2D to,
  Color color,
) {
  var x = from.x;
  var y = from.y;

  final differenceX = to.x - x;
  var differenceY = to.y - y;

  var yi = 1;
  const xi = 1;

  if (differenceY < 0) {
    differenceY = -differenceY;
    yi = -1;
  }

  var p = 2 * differenceY - differenceX;

  while (x <= to.x) {
    bitmap.setPixel(x, y, color);
    ++x;

    if (p < 0) {
      p += 2 * differenceY;

      if (differenceY > differenceX) {
        x += xi;
      }
    } else {
      p = p + 2 * differenceY - 2 * differenceX;
      y += yi;
    }
  }
}

void _drawLineX(
  Bitmap bitmap,
  Point2D from,
  Point2D to,
  Color color,
) {
  var x = from.x;
  var y = from.y;

  var differenceX = to.x - x;
  final differenceY = to.y - y;

  var xi = 1;

  if (differenceX <= 0) {
    differenceX = -differenceX;
    xi = -1;
  }

  var p = 2 * differenceX - differenceY;

  while (y <= to.y) {
    bitmap.setPixel(x, y, color);
    y++;

    if (p < 0) {
      p += 2 * differenceX;
    } else {
      p = p + 2 * differenceX - 2 * differenceY;
      x += xi;
    }
  }
}

void drawLine(
  Bitmap bitmap,
  Point2D from,
  Point2D to,
  Color color,
) {
  if (from == to) {
    bitmap.setPixel(from.x, from.y, color);
    return;
  }

  final x = from.x;
  final y = from.y;

  final differenceX = to.x - x;
  final differenceY = to.y - y;

  if (differenceY <= differenceX) {
    if (differenceY.abs() > differenceX) {
      _drawLineX(bitmap, to, from, color);
    } else {
      _drawLineY(bitmap, from, to, color);
    }
  } else {
    if (differenceX.abs() > differenceY) {
      _drawLineY(bitmap, to, from, color);
    } else {
      _drawLineX(bitmap, from, to, color);
    }
  }
}
