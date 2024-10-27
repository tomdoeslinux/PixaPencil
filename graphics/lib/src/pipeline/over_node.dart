import 'dart:typed_data';

import '../algorithms/blend_colors.dart';
import '../core/bitmap.dart';
import '../core/color.dart';
import '../core/rect.dart';
import 'node.dart';

class OverNode extends Node {
  OverNode({super.inputNode, super.auxNode});

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap operation(Rect? roi) {
    final baseBitmap = inputNode!.process(roi);
    final overlayBitmap = auxNode!.process(null);

    final numChannels = overlayBitmap.config.numChannels;

    final rowLength = overlayBitmap.width * numChannels;
    final blendedRow = Uint8List(rowLength);

    for (var y = 0; y < overlayBitmap.height; ++y) {
      final baseRowStartIndex = y * baseBitmap.width * numChannels;
      final overlayRowStartIndex = y * overlayBitmap.width * numChannels;

      for (var x = 0; x < overlayBitmap.width; ++x) {
        final basePixelIndex = baseRowStartIndex + x * numChannels;
        final overlayPixelIndex = overlayRowStartIndex + x * numChannels;

        final baseColor = Colors.rgba(
          baseBitmap.pixels[basePixelIndex],
          baseBitmap.pixels[basePixelIndex + 1],
          baseBitmap.pixels[basePixelIndex + 2],
          baseBitmap.pixels[basePixelIndex + 3],
        );

        final overlayColor = Colors.rgba(
          overlayBitmap.pixels[overlayPixelIndex],
          overlayBitmap.pixels[overlayPixelIndex + 1],
          overlayBitmap.pixels[overlayPixelIndex + 2],
          overlayBitmap.pixels[overlayPixelIndex + 3],
        );

        final blendedColor = blendColors(overlayColor, baseColor);

        blendedRow[x * numChannels] = blendedColor.r;
        blendedRow[x * numChannels + 1] = blendedColor.g;
        blendedRow[x * numChannels + 2] = blendedColor.b;
        blendedRow[x * numChannels + 3] = blendedColor.a;
      }

      baseBitmap.pixels.setRange(
        baseRowStartIndex,
        baseRowStartIndex + rowLength,
        blendedRow,
      );
    }

    return baseBitmap;
  }
}
