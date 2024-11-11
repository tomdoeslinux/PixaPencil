class GRect {
  final int x, y, width, height;

  GRect({
    required this.x,
    required this.y,
    required this.width,
    required this.height,
  });

  @override
  String toString() {
    return "Rect(x: $x, y: $y, width: $width, height: $height)";
  }
}
