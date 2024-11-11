import '../core/bitmap.dart';
import '../core/rect.dart';
import 'node.dart';

class SourceNode extends Node {
  final GBitmap source;

  SourceNode({required this.source}) : super(inputNode: null);

  @override
  GRect getRequiredROI(GRect outputRoi) {
    return outputRoi;
  }

  @override
  GBitmap operation(GRect? roi) {
    return roi != null ? source.crop(roi) : source;
  }
}
