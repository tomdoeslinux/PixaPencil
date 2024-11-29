import 'dart:math';

typedef GColor = int;

class GColors {
  static GColor red = 0xFF0000FF;
  static GColor green = 0x00FF00FF;
  static GColor blue = 0x0000FFFF;
  static GColor black = 0x0FF;
  static GColor white = 0xFFFFFFFF;
  static GColor transparent = 0x0;

  static GColor rgba(int r, int g, int b, int a) {
    return (r << 24) | (g << 16) | (b << 8) | a;
  }

  static GColor rgb(int r, int g, int b) {
    return (r << 24) | (g << 16) | (b << 8) | 0xFF;
  }

  static void rgbToHsl(int r, int g, int b, List<double> hsv) {
    final r1 = r / 255.0;
    final g1 = g / 255.0;
    final b1 = b / 255.0;

    final rgbMax = max(max(r1, g1), b1);
    final rgbMin = min(min(r1, g1), b1);

    final chroma = rgbMax - rgbMin;

    late double hue;

    if (chroma == 0) {
      hue = 0;
    } else if (rgbMax == r1) {
      hue = 60.0 * (((g1 - b1) / chroma) % 6.0);
    } else if (rgbMax == g1) {
      hue = 60.0 * (((b1 - r1) / chroma) + 2);
    } else if (rgbMax == b1) {
      hue = 60.0 * (((r1 - g1) / chroma) + 4);
    }

    final lightness = (rgbMax + rgbMin) / 2;

    late double saturation;

    if (rgbMax == 0) {
      saturation = 0;
    } else {
      saturation = chroma / (1 - (2 * lightness - 1).abs());
    }

    hsv[0] = hue.roundToDouble();
    hsv[1] = saturation;
    hsv[2] = lightness;
  }

  static GColor hslToRgb(double h, double s, double l) {
    final chroma = (1 - ((2 * l) - 1).abs()) * s;
    final x = chroma * (1 - (((h / 60) % 2) - 1).abs());
    final m = l - chroma / 2;

    late double rPrime;
    late double gPrime;
    late double bPrime;

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

    return GColors.rgb(
      ((rPrime + m) * 255.0).round(),
      ((gPrime + m) * 255.0).round(),
      ((bPrime + m) * 255.0).round(),
    );
  }
}

extension ColorExtensions on GColor {
  int get r => (this >> 24) & 0xFF;
  int get g => (this >> 16) & 0xFF;
  int get b => (this >> 8) & 0xFF;
  int get a => this & 0xFF;
}
