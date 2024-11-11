import 'dart:typed_data';
import 'color.dart';
import 'rect.dart';

enum GBitmapConfig {
  rgb(3),
  rgba(4);

  final int numChannels;

  const GBitmapConfig(this.numChannels);
}

class GBitmap {
  final int width;
  final int height;
  final GBitmapConfig config;
  final Uint8List _pixels;

  GBitmap(this.width, this.height, {required this.config})
      : _pixels = Uint8List(width * height * config.numChannels);

  GBitmap.fromPixels(this._pixels, this.width, this.height,
      {required this.config});

  ByteBuffer get buffer => _pixels.buffer;
  Uint8List get pixels => _pixels;

  int _getPixelArrayIndex(int x, int y) {
    return (y * width + x) * config.numChannels;
  }

  bool _isOutOfBounds(int x, int y) {
    return x < 0 || x >= width || y < 0 || y >= height;
  }

  GColor getPixel(int x, int y) {
    if (_isOutOfBounds(x, y)) {
      throw RangeError("(x: $x, y: $y) are out of bitmap bounds");
    }

    final index = _getPixelArrayIndex(x, y);

    if (config == GBitmapConfig.rgba) {
      return GColors.rgba(
        _pixels[index],
        _pixels[index + 1],
        _pixels[index + 2],
        _pixels[index + 3],
      );
    } else {
      return GColors.rgb(
        _pixels[index],
        _pixels[index + 1],
        _pixels[index + 2],
      );
    }
  }

  void setPixel(int x, int y, GColor color) {
    if (_isOutOfBounds(x, y)) {
      return;
    }

    final index = _getPixelArrayIndex(x, y);

    _pixels[index] = color.r;
    _pixels[index + 1] = color.g;
    _pixels[index + 2] = color.b;

    if (config == GBitmapConfig.rgba) {
      _pixels[index + 3] = color.a;
    }
  }

  void fillColor(GColor color) {
    for (var i = 0; i < _pixels.length; i += 4) {
      _pixels[i] = color.r;
      _pixels[i + 1] = color.g;
      _pixels[i + 2] = color.b;

      if (config == GBitmapConfig.rgba) {
        _pixels[i + 3] = color.a;
      }
    }
  }

  GBitmap crop(GRect region) {
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

    return GBitmap.fromPixels(
      croppedPixels,
      region.width,
      region.height,
      config: config,
    );
  }
}
