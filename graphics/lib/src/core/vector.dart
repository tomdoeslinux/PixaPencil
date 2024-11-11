import 'dart:math';

typedef GVector = (double x, double y);

extension VectorExtensions on GVector {
  double get x => $1;
  double get y => $2;

  GVector subtract(GVector v1) {
    return (x - v1.x, y - v1.y);
  }

  GVector add(GVector v1) {
    return (x + v1.x, y + v1.y);
  }

  double dot(GVector v2) {
    return x * v2.x + y * v2.y;
  }

  double magnitude() {
    return sqrt(x * x + y * y);
  }

  GVector normalize() {
    final mag = magnitude();

    if (mag == 0) {
      return (0, 0);
    }

    return (x / mag, y / mag);
  }
}
