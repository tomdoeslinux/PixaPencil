import 'dart:math';

typedef Color = int;

class Colors {
  static Color red = 0xFF0000FF;
  static Color green = 0x00FF00FF;
  static Color blue = 0x0000FFFF;
  static Color black = 0x0FF;

  static Color rgba(int r, int g, int b, int a) {
    return (r << 24) | (g << 16) | (b << 8) | a;
  }

  static Color rgb(int r, int g, int b) {
    return (r << 24) | (g << 16) | (b << 8) | 0xFF;
  }

  static void rgbToHsv(int r, int g, int b, List<double> hsv) {
    final r1 = r / 255.0;
    final g1 = g / 255.0;
    final b1 = b / 255.0;

    final rgbMax = max(max(r1, g1), b1);
    final rgbMin = min(min(r1, g1), b1);

    final chroma = rgbMax - rgbMin;

    var hue = 0.0;

    if (chroma == 0) {
      hue = 0;
    } else if (rgbMax == r1) {
      hue = 60.0 * (((g1 - b1) / chroma) % 6.0);
    } else if (rgbMax == g1) {
      hue = 60.0 * (((b1 - r1) / chroma) + 2);
    } else if (rgbMax == b1) {
      hue = 60.0 * (((r1 - g1) / chroma) + 4);
    }

    final saturation = rgbMax == 0 ? 0.0 : (chroma / rgbMax) * 100.0;

    hsv[0] = hue;
    hsv[1] = saturation / 100.0;
    hsv[2] = rgbMax;
  }

  static Color hsvToRgb(double h, double s, double v) {
    final chroma = v * s;
    final x = chroma * (1 - ((h / 60.0) % 2 - 1).abs());
    final m = v - chroma;

    var rPrime = 0.0;
    var gPrime = 0.0;
    var bPrime = 0.0;

    if (h >= 0 && h < 60) {
      rPrime = chroma;
      gPrime = x;
      bPrime = 0.0;
    } else if (h >= 60 && h < 120) {
      rPrime = x;
      gPrime = chroma;
      bPrime = 0.0;
    } else if (h >= 120 && h < 180) {
      rPrime = 0.0;
      gPrime = chroma;
      bPrime = x;
    } else if (h >= 180 && h < 240) {
      rPrime = 0.0;
      gPrime = x;
      bPrime = chroma;
    } else if (h >= 240 && h < 300) {
      rPrime = x;
      gPrime = 0.0;
      bPrime = chroma;
    } else if (h >= 300 && h < 360) {
      rPrime = chroma;
      gPrime = 0.0;
      bPrime = x;
    }

    return Colors.rgb(
      ((rPrime + m) * 255.0).round(),
      ((gPrime + m) * 255.0).round(),
      ((bPrime + m) * 255.0).round(),
    );
  }
}

extension ColorExtensions on Color {
  int get r => (this >> 24) & 0xFF;
  int get g => (this >> 16) & 0xFF;
  int get b => (this >> 8) & 0xFF;
  int get a => this & 0xFF;
}
