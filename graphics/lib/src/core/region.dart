import 'package:collection/collection.dart';
import 'package:graphics/src/core/rect.dart';

class GRegion {
  final List<GRect> rectangles;

  const GRegion(this.rectangles);

  factory GRegion.rect(GRect rect) {
    return GRegion([rect]);
  }

  GRect get boundingBox {
    var left = rectangles.first.x;
    var top = rectangles.first.y;
    var right = rectangles.first.x + rectangles.first.width;
    var bottom = rectangles.first.y + rectangles.first.height;

    for (var rect in rectangles) {
      left = left < rect.x ? left : rect.x;
      top = top < rect.y ? top : rect.y;
      right = right > rect.x + rect.width ? right : rect.x + rect.width;
      bottom = bottom > rect.y + rect.height ? bottom : rect.y + rect.height;
    }

    return GRect.fromLTRB(left, top, right, bottom);
  }

  @override
  int get hashCode => rectangles.fold(0, (prev, rect) => prev ^ rect.hashCode);

  @override
  bool operator ==(Object other) =>
      other is GRegion &&
      const ListEquality().equals(rectangles, other.rectangles);
}
