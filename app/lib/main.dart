import 'dart:async';
import 'dart:math';
import 'dart:typed_data';
import 'dart:ui' as ui;

import 'package:flutter/material.dart';
import 'package:logging/logging.dart';
import 'package:provider/provider.dart';

void main() {
  _setupLogging();
  runApp(const MyApp());
}

void _setupLogging() {
  Logger.root.level = Level.ALL;
  Logger.root.onRecord.listen((record) {
    // ignore: avoid_print
    print(
        '\x1B[33m${record.level.name}: ${record.time}: ${record.message}\x1B[0m');
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

class ToolButton extends StatelessWidget {
  final IconData icon;
  final VoidCallback onPressed;

  const ToolButton({super.key, required this.icon, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return IconButton(onPressed: onPressed, icon: Icon(icon));
  }
}

class GestureController with ChangeNotifier {
  final Logger logger = Logger("GestureController");

  double _scale = 1.0;
  double _initialScale = 1.0;
  Offset _panOffset = Offset.zero;
  Offset _initialPanOffset = Offset.zero;
  Offset _zoomOrigin = Offset.zero;

  late AnimationController _zoomAnimationController;
  late Animation<double> _zoomScaleAnimation;
  late Animation<Offset> _zoomPanAnimation;

  late AnimationController _flingAnimationController;
  late Animation<Offset> _flingAnimation;

  GestureController(TickerProvider vsync) {
    _flingAnimationController = AnimationController(vsync: vsync);
    _zoomAnimationController = AnimationController(vsync: vsync);
    
    _flingAnimationController.addListener(() {
      _panOffset = _flingAnimation.value;
      notifyListeners();
    });

    _zoomAnimationController.addListener(() {
      _scale = _zoomScaleAnimation.value;
      _panOffset = _zoomPanAnimation.value;
      notifyListeners();
    });
  }

  Offset get panOffset => _panOffset;
  double get scale => _scale;

  void onScaleStart(ScaleStartDetails details) {
    _initialScale = _scale;
    _zoomOrigin = details.localFocalPoint;
    _initialPanOffset = _panOffset;
    _flingAnimationController.stop();
  }

  void onScaleUpdate(ScaleUpdateDetails details, Offset center) {
    _flingAnimationController.stop(canceled: true);

    if (details.scale == 1) {
      // User is just panning
      _panOffset += details.focalPointDelta;
    } else {
      _scale = _initialScale * details.scale;

      final centerDelta = _zoomOrigin - center;
      final scaleRatio = _scale / _initialScale;
      final newCenter = _zoomOrigin - (centerDelta * scaleRatio);

      _panOffset = (_initialPanOffset * details.scale) + (newCenter - center);
    }

    notifyListeners();
  }

  void onDoubleTapDown(TapDownDetails details, Offset center) {
    const scaleAmount = 2.0;
    _initialScale = _scale;
    _scale *= scaleAmount;

    _zoomScaleAnimation = Tween<double>(
      begin: _initialScale,
      end: _scale,
    ).animate(
      CurvedAnimation(
          parent: _zoomAnimationController,
          curve: Curves.easeInOutCubic,
      ),
    );

    final initialPanOffset = _panOffset;
    final tapOrigin = details.localPosition;

    final centerDelta = tapOrigin - center;
    final scaleRatio = (_initialScale * scaleAmount) / _initialScale;
    final newCenter = tapOrigin - (centerDelta * scaleRatio);

    _initialPanOffset = _panOffset;
    final newPanOffset = (_initialPanOffset * scaleAmount) + (newCenter - center);

    _zoomPanAnimation = Tween<Offset>(
      begin: initialPanOffset,
      end: newPanOffset,
    ).animate(
      CurvedAnimation(
        parent: _zoomAnimationController,
        curve: Curves.easeInOutCubic,
      ),
    );

    _zoomAnimationController.duration = const Duration(milliseconds: 350);
    _zoomAnimationController.forward(from: 0.0);
  }

  void onScaleEnd(ScaleEndDetails details) {
    final velocity = details.velocity.pixelsPerSecond;
    const velocityThreshold = 900.0;

    if (velocity.distance < velocityThreshold) {
      return; // If not strong enough, don't start the animation
    }

    const deceleration = 0.01;
    final scaledVelocity = velocity * 0.5;

    final duration = (scaledVelocity.distance / deceleration).clamp(150, 600);

    final dxFinal = scaledVelocity.dx / (deceleration * 1000);
    final dyFinal = scaledVelocity.dy / (deceleration * 1000);

    _flingAnimation = Tween<Offset>(
      begin: _panOffset,
      end: _panOffset + Offset(dxFinal, dyFinal),
    ).animate(
      CurvedAnimation(
        parent: _flingAnimationController,
        curve: Curves.decelerate,
      ),
    );

    _flingAnimationController.duration = Duration(milliseconds: duration.toInt());

    // Start animation
    _flingAnimationController.forward(from: 0.0);
  }

  @override
  void dispose() {
    _flingAnimationController.dispose();
    _zoomAnimationController.dispose();
    super.dispose();
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

class _DrawingSurfaceState extends State<DrawingSurface> with TickerProviderStateMixin {
  late Rect _artboardRect;
  late final Bitmap _bitmap;
  ui.Image? _image;
  bool _moveMode = false;
  final Logger logger = Logger("DrawingSurface");

  @override
  void initState() {
    super.initState();

    _bitmap = Bitmap(100, 100);
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

    final x =
        ((artboardPosition.dx / _artboardRect.width) * _bitmap.width).toInt();
    final y =
        ((artboardPosition.dy / _artboardRect.height) * _bitmap.height).toInt();

    if (x >= 0 && x < _bitmap.width && y >= 0 && y < _bitmap.height) {
      _bitmap.setPixel(x, y, const ColorRGBA(0, 0, 0, 255));
      _updateImage();
    }
  }

  Rect _calculateArtboardRect(Size size, ui.Offset panOffset, double scale) {
    final width =
        min(size.width, _bitmap.width * (size.height / _bitmap.height)) * scale;
    final height = width * (_bitmap.height / _bitmap.width);

    return Rect.fromLTWH(
      panOffset.dx + ((size.width - width) / 2),
      panOffset.dy + ((size.height - height) / 2),
      width,
      height,
    );
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        final availableSize = constraints.biggest;

        final center = Offset(
          availableSize.width / 2,
          availableSize.height / 2,
        );

        return ChangeNotifierProvider<GestureController>(
          create: (context) => GestureController(this),
          child: Consumer<GestureController>(
            builder: (context, controller, child) {
              _artboardRect = _calculateArtboardRect(availableSize, controller.panOffset, controller.scale);

              return GestureDetector(
                onScaleStart: _moveMode ? controller.onScaleStart : null,
                onTapDown: (details) {
                  if (!_moveMode) {
                    _drawPixel(details.localPosition, constraints.biggest);
                  }
                },
                onDoubleTapDown: (details) {
                  if (_moveMode) {
                    controller.onDoubleTapDown(details, center);
                  }
                },
                onScaleUpdate: (details) {
                  if (_moveMode) {
                    controller.onScaleUpdate(details, center);
                  } else if (details.scale == 1) {
                    _drawPixel(details.localFocalPoint, constraints.biggest);
                  }
                },
                onScaleEnd: _moveMode ? controller.onScaleEnd : null,
                child: _image != null
                    ? Stack(
                        children: [
                          CustomPaint(
                            painter:
                                DrawingSurfacePainter(_image!, _artboardRect),
                            size: Size.infinite,
                          ),
                          Positioned(
                            bottom: 10,
                            right: 10,
                            child: IconButton(
                              style: IconButton.styleFrom(
                                backgroundColor: _moveMode
                                    ? Colors.green
                                    : Colors.transparent,
                                foregroundColor:
                                    _moveMode ? Colors.white : Colors.black,
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

  Rect _getSrcRect() {
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
