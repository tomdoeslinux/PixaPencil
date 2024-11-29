import 'dart:typed_data';
import 'package:graphics/src/algorithms/blend_colors.dart';
import 'package:graphics/src/utils.dart';

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

  GBitmap _toRGBA() {
    if (config.numChannels == 4) {
      return this;
    }

    final rgbaBitmap = GBitmap(width, height, config: GBitmapConfig.rgba);

    final srcPixels = pixels;
    final destPixels = rgbaBitmap.pixels;

    for (var i = 0; i < srcPixels.length; i += 3) {
      final r = srcPixels[i];
      final g = srcPixels[i + 1];
      final b = srcPixels[i + 2];

      final destIndex = (i ~/ 3) * 4;
      destPixels[destIndex] = r;
      destPixels[destIndex + 1] = g;
      destPixels[destIndex + 2] = b;
      destPixels[destIndex + 3] = 255; // set to fully opaque
    }

    return rgbaBitmap;
  }

  static GBitmap overlay(
    GBitmap baseBitmap,
    GBitmap overlayBitmap, {
    int startX = 0,
    int startY = 0,
  }) {
    final baseBounds = GRect(
      x: 0,
      y: 0,
      width: baseBitmap.width,
      height: baseBitmap.height,
    );
    final overlayBounds = GRect(
      x: startX,
      y: startY,
      width: overlayBitmap.width,
      height: overlayBitmap.height,
    );
    final outputBounds = GRect.union(baseBounds, overlayBounds);

    final baseChannels = baseBitmap.config.numChannels;
    final overlayChannels = overlayBitmap.config.numChannels;
    final outputChannels = (baseChannels == 4 || overlayChannels == 4) ? 4 : 3;

    if (outputChannels == 4) {
      if (baseChannels == 3) {
        baseBitmap = baseBitmap._toRGBA();
      } else if (overlayChannels == 3) {
        overlayBitmap = overlayBitmap._toRGBA();
      }
    }

    final outputConfig =
        outputChannels == 4 ? GBitmapConfig.rgba : GBitmapConfig.rgb;
    final outputBitmap = GBitmap(
      outputBounds.width,
      outputBounds.height,
      config: outputConfig,
    );

    final basePixels = baseBitmap.pixels;
    final baseHeight = baseBitmap.height;

    for (var y = 0; y < baseHeight; ++y) {
      final outputYStart = y + (outputBounds.height - baseBounds.height);
      final outputXStart = outputBounds.width - baseBounds.width;

      final outputRowStart =
          outputBitmap._getPixelArrayIndex(outputXStart, outputYStart);
      final outputRowEnd = outputBitmap._getPixelArrayIndex(
          baseBitmap.width + outputXStart, outputYStart);
      final baseRowStart = baseBitmap._getPixelArrayIndex(0, y);

      outputBitmap.pixels
          .setRange(outputRowStart, outputRowEnd, basePixels, baseRowStart);
    }

    final overlayPixels = overlayBitmap.pixels;
    final outputPixels = outputBitmap.pixels;

    for (var y = 0; y < overlayBitmap.height; ++y) {
      final outputY = startY + y + (outputBounds.height - baseBounds.height);

      for (var x = 0; x < overlayBitmap.width; ++x) {
        final outputX = startX + x + (outputBounds.width - baseBounds.width);

        final outputIndex = outputBitmap._getPixelArrayIndex(outputX, outputY);
        final overlayIndex = overlayBitmap._getPixelArrayIndex(x, y);

        final baseColor = GColors.rgba(
          outputPixels[outputIndex],
          outputPixels[outputIndex + 1],
          outputPixels[outputIndex + 2],
          outputChannels == 4 ? outputPixels[outputIndex + 3] : 255,
        );

        final overlayColor = GColors.rgba(
          overlayPixels[overlayIndex],
          overlayPixels[overlayIndex + 1],
          overlayPixels[overlayIndex + 2],
          overlayChannels == 4 ? overlayPixels[overlayIndex + 3] : 255,
        );

        final blendedColor = blendColors(overlayColor, baseColor);

        outputPixels[outputIndex] = blendedColor.r;
        outputPixels[outputIndex + 1] = blendedColor.g;
        outputPixels[outputIndex + 2] = blendedColor.b;

        if (outputChannels == 4) {
          outputPixels[outputIndex + 3] = blendedColor.a;
        }
      }
    }

    return outputBitmap;
  }
}
