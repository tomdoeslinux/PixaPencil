import "../core/bitmap.dart";
import "../core/rect.dart";

abstract class Node {
  static var _idCounter = 0;
  final int id;
  Node? inputNode;
  Node? parentNode;
  Node? auxNode;

  Bitmap? _cache;
  var _cacheHits = 0;

  Node({required this.inputNode, this.auxNode}) : id = _generateId() {
    inputNode?.parentNode = this;
    auxNode?.parentNode = this;
  }

  int get cacheHits => _cacheHits;
  bool get hasCache => _cache != null;

  void invalidateCache() {
    _cache = null;
    _cacheHits = 0;
    parentNode?.invalidateCache();
  }

  static int _generateId() {
    return _idCounter++;
  }

  // Calculates the region of the source image that is necessary to process a
  // ...specific Region of Interest (ROI) in the output.
  Rect getRequiredROI(Rect outputRoi);

  Bitmap operation(Rect? roi);

  Bitmap process(Rect? roi) {
    if (_cache != null) {
      ++_cacheHits;
      return _cache!;
    }

    final bitmap = operation(roi);
    _cache = bitmap;

    return bitmap;
  }
}
