import '../core/color.dart';

Color interpolateColors(Color c0, Color c1, double factor) {
  final r = (c0.r + factor * (c1.r - c0.r)).round();
  final g = (c0.g + factor * (c1.g - c0.g)).round();
  final b = (c0.b + factor * (c1.b - c0.b)).round();
  final a = (c0.a + factor * (c1.a - c0.a)).round();

  return Colors.rgba(r, g, b, a);
}
