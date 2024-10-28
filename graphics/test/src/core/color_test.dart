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

    test("rgb converts to hsl properly", () {
      final hslValues = List.filled(3, 0.0);

      Colors.rgbToHsl(96, 4, 66, hslValues);

      print(hslValues);

      expect(hslValues.map((c) => double.parse(c.toStringAsFixed(3))), [320.000, 0.920, 0.196]);
    });

    
    test("rgb converts to hsl properly (2)", () {
      final hslValues = List.filled(3, 0.0);

      Colors.rgbToHsl(15, 145, 231, hslValues);

      expect(hslValues.map((c) => double.parse(c.toStringAsFixed(3))), [204.000, 0.878, 0.482]);
    });

    test("hsl converts to rgb properly", () {
      final color = Colors.hslToRgb(44, 0.99, 0.32);

      expect(color.r, 162);
      expect(color.g, 119);
      expect(color.b, 1);
    });

    test("hsl converts to rgb properly (2)", () {
      final color = Colors.hslToRgb(7, 1, 0.16);

      expect(color.r, 82);
      expect(color.g, 10);
      expect(color.b, 0);
    });
  });
}
