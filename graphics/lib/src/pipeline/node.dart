import "package:graphics/src/core/region.dart";
import "package:graphics/src/utils.dart";

import "../core/bitmap.dart";
import "../core/rect.dart";

abstract class Node {
  final Map<GRect, GBitmap> _cache = {};

  static var _idCounter = 0;
  final int id;

  Node? _inputNode;
  Node? _auxNode;
  Node? parentNode;

  Node({required Node? inputNode, Node? auxNode})
      : id = _generateId(),
        _inputNode = inputNode,
        _auxNode = auxNode {
    _inputNode?.parentNode = this;
    _auxNode?.parentNode = this;
  }

  Node? get inputNode => _inputNode;
  set inputNode(Node? node) {
    _inputNode = node;
    node?.parentNode = this;
  }

  Node? get auxNode => _auxNode;
  set auxNode(Node? node) {
    _auxNode = node;
    node?.parentNode = this;
  }

  static int _generateId() {
    return _idCounter++;
  }

  GRect get boundingBox;

  GBitmap operation(GRegion roi);

  // todo not sure if roi should be nulalble
  GBitmap process(GRegion roi) {
    // final adjustedRoi = GRect.intersection(roi!, boundingBox);

    if (!_cache.containsKey(roi)) {
      final bitmap = operation(roi);

      _cache[boundingBox] = bitmap.crop(boundingBox);

      return bitmap;
    } else {
      return _cache[roi]!;
    }
  }
}
