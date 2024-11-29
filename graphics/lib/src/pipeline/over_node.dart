import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/rect.dart';
import 'package:graphics/src/core/region.dart';
import 'package:graphics/src/pipeline/node.dart';


class OverlayNode extends Node {
  OverlayNode({super.inputNode, super.auxNode});

  @override
  GBitmap operation(GRegion roi) {
    final baseBitmap = inputNode!.process(roi);
        final overlayBitmap = inputNode!.process(roi);

    // final overlayBitmap = auxNode!.process(null);

    final overlayedBitmap = GBitmap.overlay(baseBitmap, overlayBitmap);

    return overlayedBitmap;
  }

  @override
  GRect get boundingBox =>
      GRect.union(inputNode!.boundingBox, auxNode!.boundingBox);
}
