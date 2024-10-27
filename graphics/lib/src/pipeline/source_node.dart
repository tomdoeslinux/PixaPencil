import '../core/bitmap.dart';
import '../core/rect.dart';
import 'node.dart';

class SourceNode extends Node {
  final Bitmap source;

  SourceNode({required this.source}) : super(inputNode: null);

  @override
  Rect getRequiredROI(Rect outputRoi) {
    return outputRoi;
  }

  @override
  Bitmap operation(Rect? roi) {
    return roi != null ? source.crop(roi) : source;
  }
}
