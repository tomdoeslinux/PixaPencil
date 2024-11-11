import 'dart:async';
import 'dart:ui' as ui;

import 'package:graphics/graphics.dart';

extension GBitmapExtensions on GBitmap {
  Future<ui.Image> toFlutterImage() {
    final completer = Completer<ui.Image>();

    ui.decodeImageFromPixels(
      pixels,
      width,
      height,
      ui.PixelFormat.rgba8888,
      (img) => completer.complete(img),
    );

    return completer.future;
  }
}