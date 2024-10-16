import 'dart:typed_data';
import 'color.dart';
import 'rect.dart';

enum BitmapConfig {
  rgb(3),
  rgba(4);

  final int numChannels;

  const BitmapConfig(this.numChannels);
}

class Bitmap {
  final int width;
  final int height;
  final BitmapConfig config;
  final Uint8List _pixels;

  Bitmap(this.width, this.height, {required this.config})
      : _pixels = Uint8List(width * height * config.numChannels);

  Bitmap.fromPixels(this._pixels, this.width, this.height,
      {required this.config});

  ByteBuffer get buffer => _pixels.buffer;
  Uint8List get pixels => _pixels;

  int _getPixelArrayIndex(int x, int y) {
    return (y * width + x) * config.numChannels;
  }

  bool _isOutOfBounds(int x, int y) {
    return x < 0 || x >= width || y < 0 || y >= height;
  }

  Color getPixel(int x, int y) {
    if (_isOutOfBounds(x, y)) {
      throw RangeError("(x: $x, y: $y) are out of bitmap bounds");
    }

    final index = _getPixelArrayIndex(x, y);

    if (config == BitmapConfig.rgba) {
      return Colors.rgba(
        _pixels[index],
        _pixels[index + 1],
        _pixels[index + 2],
        _pixels[index + 3],
      );
    } else {
      return Colors.rgb(
        _pixels[index],
        _pixels[index + 1],
        _pixels[index + 2],
      );
    }
  }

  void setPixel(int x, int y, Color color) {
    if (_isOutOfBounds(x, y)) {
      throw RangeError("(x: $x, y: $y) are out of bitmap bounds");
    }

    final index = _getPixelArrayIndex(x, y);

    _pixels[index] = color.r;
    _pixels[index + 1] = color.g;
    _pixels[index + 2] = color.b;

    if (config == BitmapConfig.rgba) {
      _pixels[index + 3] = color.a;
    }
  }

  void fillColor(Color color) {
    for (var i = 0; i < _pixels.length; i += 4) {
      _pixels[i] = color.r;
      _pixels[i + 1] = color.g;
      _pixels[i + 2] = color.b;

      if (config == BitmapConfig.rgba) {
        _pixels[i + 3] = color.a;
      }
    }
  }

  Bitmap crop(Rect region) {
    final startX = region.x;
    final startY = region.y;

    final regionWidth = region.width;
    final regionHeight = region.height;

    final endX = startX + regionWidth;
    final endY = startY + regionHeight;

    if (startX < 0 || startY < 0 || endX > width || endY > height) {
      throw ArgumentError("Crop rect is out of bounds");
    }

    final croppedPixels =
        Uint8List(regionWidth * regionHeight * config.numChannels);

    for (var y = startY; y < endY; ++y) {
      final srcStart = _getPixelArrayIndex(startX, y);

      final destStart = ((y - startY) * regionWidth) * config.numChannels;
      final destEnd = destStart + regionWidth * config.numChannels;

      croppedPixels.setRange(destStart, destEnd, _pixels, srcStart);
    }

    return Bitmap.fromPixels(
      croppedPixels,
      region.width,
      region.height,
      config: config,
    );
  }
}
