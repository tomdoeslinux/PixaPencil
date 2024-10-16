import 'dart:math';

typedef Vector = (double x, double y);

extension VectorUtils on Vector {
  double get x => $1;
  double get y => $2;

  Vector subtract(Vector v1) {
    return (x - v1.x, y - v1.y);
  }

  Vector add(Vector v1) {
    return (x + v1.x, y + v1.y);
  }

  double dot(Vector v2) {
    return x * v2.x + y * v2.y;
  }

  double magnitude() {
    return sqrt(x * x + y * y);
  }

  Vector normalize() {
    final mag = magnitude();

    if (mag == 0) {
      return (0, 0);
    }

    return (x / mag, y / mag);
  }
}
