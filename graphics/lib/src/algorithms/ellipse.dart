
import 'package:graphics/src/algorithms/line.dart';
import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/color.dart';
import 'package:graphics/src/core/point.dart';

void _drawMidpointEllipse({
  required GBitmap bitmap,
  required bool adjustX,
  required bool adjustY,
  required GPoint midpoint,
  required int radiusX,
  required int radiusY,
  required GColor fillColor,
  required GColor strokeColor,
  required GColor strokeThickness,
}) {
  void putPixel(GPoint p1, GPoint p2) {
    final xc = p1.x;
    final yc = p1.y;

    final x = p2.x;
    final y = p2.y;

    if (!adjustX && !adjustY) {
      bitmap.setPixel(xc + x, yc + y, strokeColor);
      bitmap.setPixel(xc + x, yc - y, strokeColor);
      bitmap.setPixel(xc - x, yc - y, strokeColor);
      bitmap.setPixel(
        xc - x,
        yc + y,
        strokeColor,
      );

      if (fillColor.a > 0) {
        drawLine(
          bitmap,
          (x: xc + x, y: yc + y),
          (x: xc + x, y: yc - y),
          fillColor,
        );

        drawLine(
          bitmap,
          (x: xc - x, y: yc - y),
          (x: xc - x, y: yc + y),
          fillColor,
        );
      }
    } else if (adjustX && !adjustY) {
      bitmap.setPixel((xc + x) + 1, yc + y, fillColor);
      bitmap.setPixel((xc + x) + 1, yc - y, fillColor);
      bitmap.setPixel(xc - x, yc - y, fillColor);
      bitmap.setPixel(xc - x, yc + y, fillColor);

      if (fillColor.a > 0) {
        drawLine(
          bitmap,
          (x: (xc + x) + 1, y: yc + y),
          (x: (xc + x) + 1, y: yc - y),
          fillColor,
        );

        drawLine(
          bitmap,
          (x: xc - x, y: yc - y),
          (x: xc - x, y: yc + y),
          fillColor,
        );
      }
    } else if (!adjustX) {
      bitmap.setPixel(xc + x, (yc + y) + 1, fillColor);
      bitmap.setPixel(xc + x, yc - y, fillColor);
      bitmap.setPixel(xc - x, yc - y, fillColor);
      bitmap.setPixel(xc - x, (yc + y) + 1, fillColor);

      if (fillColor.a > 0) {
        drawLine(
          bitmap,
          (x: xc + x, y: (yc + y) + 1),
          (x: xc + x, y: yc - y),
          fillColor,
        );

        drawLine(
          bitmap,
          (x: xc - x, y: yc - y),
          (x: xc - x, y: (yc + y) + 1),
          fillColor,
        );
      }
    } else {
      bitmap.setPixel((xc + x) + 1, (yc + y) + 1, fillColor);
      bitmap.setPixel((xc + x) + 1, yc - y, fillColor);
      bitmap.setPixel(xc - x, yc - y, fillColor);
      bitmap.setPixel(xc - x, (yc + y) + 1, fillColor);

      if (fillColor.a > 0) {
        drawLine(
          bitmap,
          (x: (xc + x) + 1, y: (yc + y) + 1),
          (x: (xc + x) + 1, y: yc - y),
          fillColor,
        );

        drawLine(
          bitmap,
          (x: xc - x, y: yc - y),
          (x: xc - x, y: (yc + y) + 1),
          fillColor,
        );
      }
    }
  }

  final GPoint idp = (x: 0, y: radiusY);

  var xkp1 = idp.x;
  var ykp1 = idp.y;

  late int lxkp1;
  late int lykp1;

  var p1k = (radiusY * radiusY) +
      ((radiusX * radiusX) / 4) -
      (radiusY * (radiusX * radiusX));

  final incy = midpoint.y;
  final incx = midpoint.x;

  putPixel((x: incx, y: incy), (x: xkp1, y: ykp1));

  while ((2 * (xkp1 + 1) * (radiusY * radiusY)) <
      (2 * ykp1 * (radiusX * radiusX))) {
    if (p1k >= 0) {
      xkp1++;
      ykp1--;

      lxkp1 = xkp1 - 1;
      lykp1 = ykp1 + 1;

      p1k += (radiusY * radiusY) +
          (2 * (lxkp1 + 1)) * (radiusY * radiusY) +
          (radiusX * radiusX) * ((ykp1 * ykp1) - (lykp1 * lykp1)) -
          (radiusX * radiusX) * (ykp1 - lykp1);
    } else {
      xkp1++;

      lxkp1 = xkp1 - 1;
      lykp1 = ykp1;

      p1k += (radiusY * radiusY) +
          (2 * (lxkp1 + 1)) * (radiusY * radiusY) +
          (radiusX * radiusX) * ((ykp1 * ykp1) - (lykp1 * lykp1));
    }

    putPixel((x: incx, y: incy), (x: xkp1, y: ykp1));
  }

  var p2k = (radiusY.toDouble() * radiusY.toDouble()) *
          ((xkp1.toDouble() + 0.5) * (xkp1.toDouble() + 0.5)) +
      (radiusX.toDouble() * radiusX.toDouble()) *
          ((ykp1.toDouble() - 1) * (ykp1.toDouble() - 1)) -
      ((radiusX.toDouble() * radiusX.toDouble()) *
          (radiusY.toDouble() * radiusY.toDouble()));

  while (ykp1 > 0) {
    if (p2k >= 0) {
      ykp1--;
      lykp1 = ykp1 + 1;
      lxkp1 = xkp1;

      p2k += (radiusX * radiusX) -
          2 * (radiusX * radiusX) * (lykp1 - 1) +
          (radiusY * radiusY) * ((xkp1 * xkp1) - (lxkp1 * lxkp1));
    } else {
      xkp1++;
      lxkp1 = xkp1 - 1;
      ykp1--;
      lykp1 = ykp1 + 1;

      p2k += (radiusX * radiusX) -
          2 * (radiusX * radiusX) * (lykp1 - 1) +
          (radiusY * radiusY) * ((xkp1 * xkp1) - (lxkp1 * lxkp1)) +
          (radiusY * radiusY) * (xkp1 - lxkp1);
    }

    putPixel((x: incx, y: incy), (x: xkp1, y: ykp1));
  }
}

void drawEllipse({
  required GBitmap bitmap,
  required GPoint from,
  required GPoint to,
  required GColor fillColor,
  required GColor strokeColor,
  required GColor strokeThickness,
}) {
  final x1d = from.x;
  final y1d = from.y;

  final x2d = to.x;
  final y2d = to.y;

  final midpointX = (x1d + x2d) / 2;
  final midpointY = (y1d + y2d) / 2;

  final rY = ((to.y - from.y) / 2).abs().toInt();
  final rX = ((to.x - from.x) / 2).abs().toInt();

  late bool adjustX;
  late bool adjustY;

  if (midpointX % 1.0 == 0.0 && midpointY % 1.0 == 0.0) {
    adjustX = false;
    adjustY = false;
  } else if (midpointX % 1.0 != 0.0 && midpointY % 1.0 == 0.0) {
    adjustX = true;
    adjustY = false;
  } else if (midpointX % 1.0 == 0.0 && midpointY % 1.0 != 0.0) {
    adjustX = false;
    adjustY = true;
  } else {
    adjustX = true;
    adjustY = true;
  }

  _drawMidpointEllipse(
    bitmap: bitmap,
    adjustX: adjustX,
    adjustY: adjustY,
    midpoint: (x: midpointX.toInt(), y: midpointY.toInt()),
    radiusX: rX,
    radiusY: rY,
    fillColor: fillColor,
    strokeColor: strokeColor,
    strokeThickness: strokeThickness,
  );
}
