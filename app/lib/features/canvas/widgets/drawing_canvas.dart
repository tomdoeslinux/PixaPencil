import 'dart:async';
import 'dart:math';
import 'dart:ui' as ui;
import 'package:flutter/material.dart';
import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/core/color.dart' as graphics;

extension FlutterBitmapExtension on Bitmap {
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

class DrawingCanvas extends StatefulWidget {
  const DrawingCanvas({super.key});

  @override
  State<DrawingCanvas> createState() => _DrawingCanvasState();
}

class _DrawingCanvasState extends State<DrawingCanvas> {
  late Bitmap _bitmap;
  ui.Image? _bitmapImage;

  Future<void> updateBitmapImage() async {
    final updatedImage = await _bitmap.toFlutterImage();

    setState(() {
      _bitmapImage = updatedImage;
    });
  }

  @override
  void initState() {
    super.initState();

    _bitmap = Bitmap(10, 10, config: BitmapConfig.rgba);
    _bitmap.setPixel(0, 0, graphics.Colors.black);
    updateBitmapImage();
  }

  @override
  Widget build(BuildContext context) {
    if (_bitmapImage == null) {
      return const Text("Loading...");
    }

    return CustomPaint(
      painter: _CanvasPainter(_bitmapImage!, 0, Offset.zero),
      size: Size.infinite,
    );
  }
}

class _CanvasPainter extends CustomPainter {
  final ui.Image _image;
  final double _zoom;
  final Offset _panOffset;

  late Rect _artboardRect;
  final _artboardPaint = Paint()..color = Colors.white;

  _CanvasPainter(this._image, this._zoom, this._panOffset);

  void _calculateArtboardRect(Size canvasSize) {
    final width = min(
        canvasSize.width, _image.width * (canvasSize.height / _image.height));
    final height = width * (_image.height / _image.width);

    final left = (canvasSize.width - width) / 2;
    final top = (canvasSize.height - height) / 2;

    _artboardRect = Rect.fromLTWH(left, top, width, height);
  }

  Rect _getSrcRect() {
    return Rect.fromLTWH(
      0,
      0,
      _image.width.toDouble(),
      _image.height.toDouble(),
    );
  }

  @override
  void paint(Canvas canvas, Size size) {
    _calculateArtboardRect(size);

    canvas.drawColor(Colors.white70, BlendMode.src);
    canvas.drawRect(_artboardRect, _artboardPaint);
    canvas.drawImageRect(_image, _getSrcRect(), _artboardRect, Paint());
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    return true;
  }
}
