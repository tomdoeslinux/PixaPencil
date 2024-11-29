import 'package:graphics/src/core/region.dart';

import '../core/bitmap.dart';
import '../core/rect.dart';
import 'node.dart';

class SourceNode extends Node {
  final GBitmap source;

  SourceNode({required this.source}) : super(inputNode: null);

  @override
  GBitmap operation(GRegion roi) {
    final roiBbox = roi.boundingBox;
    var bmpToReturn = GBitmap(roiBbox.width, roiBbox.height, config: source.config);
    
    for (var rect in roi.rectangles) {
      bmpToReturn = GBitmap.overlay(bmpToReturn, source.crop(rect));
    }

    return bmpToReturn;
  }

  @override
  GRect get boundingBox =>
      GRect(x: 0, y: 0, width: source.width, height: source.height);
}
