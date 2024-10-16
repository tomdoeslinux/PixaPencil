import 'package:graphics_core/src/algorithms/blend_colors.dart';
import 'package:graphics_core/src/core/color.dart';
import 'package:test/test.dart';

void main() {
  group("Color blending tests", () {
    test("opaque red blends properly over opaque blue", () {
      final foreground = Colors.rgba(255, 0, 0, 255);
      final background = Colors.rgba(0, 0, 255, 255);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, equals(foreground));
    });

    test("semi opaque green blends properly over opaque yellow", () {
      final foreground = Colors.rgba(0, 128, 0, 128);
      final background = Colors.rgba(255, 255, 0, 255);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, equals(0x7fc000FF));
    });

    test("transparent blue blends properly over opaque green", () {
      final foreground = Colors.rgba(0, 0, 255, 0);
      final background = Colors.rgba(0, 255, 0, 255);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, equals(0x00FF00FF));
    });

    test("opaque red blends properly over transparent blue", () {
      final foreground = Colors.rgba(255, 0, 0, 255);
      final background = Colors.rgba(0, 0, 255, 0);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, equals(0xFF0000FF));
    });

    test("opaque red blends properly over opaque red", () {
      final foreground = Colors.rgba(255, 0, 0, 255);
      final background = Colors.rgba(255, 0, 0, 255);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, 0xFF0000FF);
    });

    test("semi opaque green blends properly over semi opaque blue", () {
      final foreground = Colors.rgba(0, 255, 0, 75);
      final background = Colors.rgba(0, 0, 255, 75);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, 0x00966A80);
    });

    test("transparent color blends properly over transparent color", () {
      final foreground = Colors.rgba(0, 0, 0, 0);
      final background = Colors.rgba(0, 0, 0, 0);

      final blendedColor = blendColors(foreground, background);

      expect(blendedColor, 0);
    });
  });
}
