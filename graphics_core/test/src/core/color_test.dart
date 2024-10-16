import 'package:graphics_core/src/core/color.dart';
import 'package:test/test.dart';

void main() {
  group("Color tests", () {
    test("rgba creates correct color value", () {
      final color = Colors.rgba(255, 128, 64, 54);
      expect(color, equals(0xFF804036));
    });

    test("rgb creates correct color value", () {
      final color = Colors.rgb(79, 24, 121);
      expect(color, equals(0x4F1879FF));
    });

    test("r, g, b, components are correct", () {
      final color = Colors.rgb(79, 24, 121);

      final r = color.r;
      expect(r, equals(79));

      final g = color.g;
      expect(g, equals(24));

      final b = color.b;
      expect(b, equals(121));

      // a should implicitly be 255 for alignment purposes
      final a = color.a;
      expect(a, equals(255));
    });

    test("r, g, b, a, components are correct", () {
      final color = Colors.rgba(22, 84, 95, 43);

      final r = color.r;
      expect(r, equals(22));

      final g = color.g;
      expect(g, equals(84));

      final b = color.b;
      expect(b, equals(95));

      final a = color.a;
      expect(a, equals(43));
    });
  });
}
