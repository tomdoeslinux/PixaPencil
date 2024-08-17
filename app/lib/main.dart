import 'dart:async';
import 'dart:math';
import 'dart:typed_data';
import 'dart:ui' as ui;

import 'package:flutter/material.dart';
import 'package:flutter/physics.dart';
import 'package:logging/logging.dart';

void main() {
  _setupLogging();
  runApp(const MyApp());
}

void _setupLogging() {
  Logger.root.level = Level.ALL;
  Logger.root.onRecord.listen((record) {
    // ignore: avoid_print
    print('\x1B[33m${record.level.name}: ${record.time}: ${record.message}\x1B[0m');
  });
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

class _DrawingSurfaceState extends State<DrawingSurface> with SingleTickerProviderStateMixin {
  late ui.Rect _artboardRect;
  late final Bitmap _bitmap;
  ui.Image? _image;
  double _scale = 1.0;
  double _initialScale = 1.0;
  Offset _panOffset = Offset.zero;
  late Offset _initialPanOffset;
  late Offset _zoomOrigin;
  bool _moveMode = false;
  final Logger logger = Logger("DrawingSurface");
  late AnimationController _animationController;
  Animation<ui.Offset>? _flingAnimation;

  @override
  void initState() {
    super.initState();

    _bitmap = Bitmap(100, 100);
    _updateImage();
    _animationController = AnimationController(vsync: this)
      ..addListener(() {
        setState(() {
          _panOffset = _flingAnimation!.value;
        });
      });
  }

  Future<void> _updateImage() async {
    final updatedImage = await _bitmap.toImage();

    setState(() {
      _image = updatedImage;
    });
  }

  void _drawPixel(Offset localPosition, Size size) {
    final artboardPosition = localPosition - _artboardRect.topLeft;

    final x = ((artboardPosition.dx / _artboardRect.width) * _bitmap.width).toInt();
    final y = ((artboardPosition.dy / _artboardRect.height) * _bitmap.height).toInt();

    if (x >= 0 && x < _bitmap.width && y >= 0 && y < _bitmap.height) {
      _bitmap.setPixel(x, y, const ColorRGBA(0, 0, 0, 255));
      _updateImage();
    }
  }

  ui.Rect _calculateArtboardRect(Size size) {
    final width = min(size.width, _bitmap.width * (size.height / _bitmap.height)) * _scale;
    final height = width * (_bitmap.height / _bitmap.width);

    return Rect.fromLTWH(
      _panOffset.dx + ((size.width - width) / 2),
      _panOffset.dy + ((size.height - height) / 2),
      width,
      height,
    );
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        final availableSize = constraints.biggest;
        _artboardRect = _calculateArtboardRect(availableSize);

        final center = Offset(
          availableSize.width / 2,
          availableSize.height / 2,
        );

        return GestureDetector(
          onScaleStart: (details) {
            _initialScale = _scale;
            _zoomOrigin = details.localFocalPoint;
            _initialPanOffset = _panOffset;
            _animationController.stop();
          },
          onScaleUpdate: (details) {
            if (_moveMode) {
              setState(() {
                if (details.scale == 1) {
                  _panOffset += details.focalPointDelta;
                } else {
                  _scale = _initialScale * details.scale;

                  final centerDelta = _zoomOrigin - center;
                  final scaleRatio = _scale / _initialScale;
                  final newCenter = _zoomOrigin - (centerDelta * scaleRatio);

                  _panOffset = (_initialPanOffset * details.scale) + (newCenter - center);
                }
              });
            } else if (details.scale == 1) {
              _drawPixel(details.localFocalPoint, constraints.biggest);
            }
          },
          onTapDown: (details) {
            if (!_moveMode) {
              _drawPixel(details.localPosition, constraints.biggest);
            }
          },
          onScaleEnd: (details) {
            if (_moveMode) {
              final velocity = details.velocity.pixelsPerSecond;
              const velocityThreshold = 900.0;

              if (velocity.distance < velocityThreshold) {
                logger.info("WEAK");
                return; // If not strong enough, don't start the animation
              }

              const deceleration = 0.01;
              final scaledVelocity = velocity * 0.5;

              final duration = (scaledVelocity.distance / deceleration).clamp(
                  150, 600);

              logger.info(duration);
              final dxFinal = scaledVelocity.dx / (deceleration * 1000);
              final dyFinal = scaledVelocity.dy / (deceleration * 1000);

              _flingAnimation = Tween<Offset>(
                begin: _panOffset,
                end: _panOffset + Offset(dxFinal, dyFinal),
              ).animate(
                CurvedAnimation(
                  parent: _animationController,
                  curve: Curves.decelerate,
                ),
              );

              _animationController.duration =
                  Duration(milliseconds: duration.toInt());

              // Start animation
              _animationController.forward(from: 0.0);
            }
          },
          child: _image != null
              ? Stack(
                  children: [
                    CustomPaint(
                      painter: DrawingSurfacePainter(_image!, _artboardRect),
                      size: Size.infinite,
                    ),
                    Positioned(
                      bottom: 10,
                      right: 10,
                      child: IconButton(
                        style: IconButton.styleFrom(
                          backgroundColor: _moveMode ? Colors.green : Colors.transparent,
                          foregroundColor: _moveMode ? Colors.white : Colors.black,
                        ),
                        iconSize: 64.0,
                        onPressed: () {
                          setState(() {
                            _moveMode = !_moveMode;
                          });
                        },
                        icon: const Icon(Icons.pan_tool_outlined),
                      ),
                    ),
                  ],
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
