import 'dart:async';
import 'dart:math';
import 'dart:typed_data';
import 'dart:ui' as ui;

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "PixaPencil",
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("PixaPencil"),
      ),
      body: const DrawingSurface(),
    );
  }
}

class DrawingSurface extends StatefulWidget {
  const DrawingSurface({super.key});

  @override
  State<StatefulWidget> createState() {
    return _DrawingSurfaceState();
  }
}

class _DrawingSurfaceState extends State<DrawingSurface> {
  late ui.Rect _artboardRect;
  late Bitmap _bitmap;
  ui.Image? _image;

  @override
  void initState() {
    super.initState();

    _bitmap = Bitmap(100, 50);
    _updateImage();
  }

  Future<void> _updateImage() async {
    final updatedImage = await _bitmap.toImage();

    setState(() {
      _image = updatedImage;
    });
  }

  void _drawPixel(Offset localPosition, Size size) {
    final artboardPosition = localPosition - _artboardRect.topLeft;
    print("Joe biden " + artboardPosition.toString());

    final x = ((artboardPosition.dx / _artboardRect.width) * _bitmap.width).toInt();
    final y = ((artboardPosition.dy / _artboardRect.height) * _bitmap.height).toInt();

    if (x >= 0 && x < _bitmap.width && y >= 0 && y < _bitmap.height) {
      _bitmap.setPixel(x, y, const ColorRGBA(0, 0, 0, 255));
      _updateImage();
    }
  }

  ui.Rect _calculateArtboardRect(Size size) {
    final centerOffset = Offset(size.width / 2, size.height / 2);

    final width = min(size.width, _bitmap.width * (size.height / _bitmap.height));
    final height = width * (_bitmap.height / _bitmap.width);

    return Rect.fromCenter(
      center: centerOffset,
      width: width,
      height: height,
    );
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        final availableSize = constraints.biggest;
        _artboardRect = _calculateArtboardRect(availableSize);

        return GestureDetector(
          onPanUpdate: (details) {
            _drawPixel(details.localPosition, constraints.biggest);
          },
          onTapDown: (details) {
            _drawPixel(details.localPosition, constraints.biggest);
          },
          child: _image != null
              ? CustomPaint(
                  painter: DrawingSurfacePainter(_image!, _artboardRect),
                  size: Size.infinite,
                )
              : const Center(
                  child: CircularProgressIndicator(),
                ),
        );
      },
    );
  }
}

class DrawingSurfacePainter extends CustomPainter {
  final ui.Image image;
  final Rect artboardRect;

  DrawingSurfacePainter(this.image, this.artboardRect);

  ui.Rect _getSrcRect() {
    return Rect.fromLTWH(0, 0, image.width.toDouble(), image.height.toDouble());
  }

  @override
  void paint(Canvas canvas, Size size) {
    canvas.drawColor(Colors.blue, BlendMode.src);

    canvas.drawRect(artboardRect, Paint()..color = Colors.white);
    canvas.drawImageRect(image, _getSrcRect(), artboardRect, Paint());
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    return false;
  }
}

class ColorRGBA {
  final int r;
  final int g;
  final int b;
  final int a;

  const ColorRGBA(this.r, this.g, this.b, this.a);
}

class Bitmap {
  static const _bytesPerPixel = 4;

  final int width;
  final int height;
  final Uint8List _pixels;

  Bitmap(this.width, this.height)
      : _pixels = Uint8List(width * height * _bytesPerPixel);

  int _getIndex(int x, int y) {
    return (y * width + x) * _bytesPerPixel;
  }

  ColorRGBA getPixel(int x, int y) {
    final index = _getIndex(x, y);

    return ColorRGBA(
      _pixels[index],
      _pixels[index + 1],
      _pixels[index + 2],
      _pixels[index + 3],
    );
  }

  void setPixel(int x, int y, ColorRGBA color) {
    final index = _getIndex(x, y);

    _pixels[index] = color.r;
    _pixels[index + 1] = color.g;
    _pixels[index + 2] = color.b;
    _pixels[index + 3] = color.a;
  }

  Future<ui.Image> toImage() {
    final completer = Completer<ui.Image>();

    ui.decodeImageFromPixels(
      _pixels,
      width,
      height,
      ui.PixelFormat.rgba8888,
      (img) => completer.complete(img),
    );

    return completer.future;
  }
}
