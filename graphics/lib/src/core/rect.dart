import 'package:graphics/src/core/point.dart';

class GRect {
  final int x, y, width, height;

  const GRect({
    required this.x,
    required this.y,
    required this.width,
    required this.height,
  });

  factory GRect.fromLTRB(
    int left,
    int top,
    int right,
    int bottom,
  ) {
    return GRect(x: left, y: top, width: right - left, height: bottom - top);
  }

  @override
  String toString() {
    return "GRect(x: $x, y: $y, width: $width, height: $height)";
  }

  @override
  bool operator ==(Object other) =>
      other is GRect &&
      other.x == x &&
      other.y == y &&
      other.width == width &&
      other.height == height;

  @override
  int get hashCode => Object.hash(x, y, width, height);

  static GRect union(GRect a, GRect b) {
    final left = a.x < b.x ? a.x : b.x;
    final top = a.y < b.y ? a.y : b.y;
    final right =
        (a.x + a.width) > (b.x + b.width) ? (a.x + a.width) : (b.x + b.width);
    final bottom = (a.y + a.height) > (b.y + b.height)
        ? (a.y + a.height)
        : (b.y + b.height);

    return GRect.fromLTRB(left, top, right, bottom);
  }

  static GRect between(GPoint from, GPoint to) {
    final left = from.x < to.x ? from.x : to.x;
    final top = from.y < to.y ? from.y : to.y;
    final right = from.x > to.x ? from.x : to.x;
    final bottom = from.y > to.y ? from.y : to.y;

    return GRect.fromLTRB(left, top, right, bottom);
  }

  // todo might not be needed
  static GRect intersection(GRect a, GRect b) {
    final left = (a.x > b.x) ? a.x : b.x;
    final top = (a.y > b.y) ? a.y : b.y;
    final right =
        (a.x + a.width < b.x + b.width) ? a.x + a.width : b.x + b.width;
    final bottom =
        (a.y + a.height < b.y + b.height) ? a.y + a.height : b.y + b.height;

    return GRect.fromLTRB(left, top, right, bottom);
  }
}
