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
}

extension ColorExtensions on Color {
  int get r => (this >> 24) & 0xFF;
  int get g => (this >> 16) & 0xFF;
  int get b => (this >> 8) & 0xFF;
  int get a => this & 0xFF;
}