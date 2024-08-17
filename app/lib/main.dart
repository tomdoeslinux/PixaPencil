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
  final bool isSelected;

  const ToolButton({
    super.key,
    required this.icon,
    required this.onPressed,
    required this.isSelected,
  });

  @override
  Widget build(BuildContext context) {
    return IconButton(
      style: IconButton.styleFrom(
        backgroundColor: isSelected ? Colors.orange : Colors.white,
      ),
      onPressed: onPressed,
      icon: Icon(icon),
    );
  }
}

class LineAlgorithm {
  final Bitmap _bitmap;

  LineAlgorithm(this._bitmap);

  void _drawLineY(Point from, Point to) {
    var x = from.x;
    var y = from.y;

    final differenceX = to.x - x;
    var differenceY = to.y - y;

    var yi = 1;
    const xi = 1;

    if (differenceY < 0) {
      differenceY = -differenceY;
      yi = -1;
    }

    var p = 2 * differenceY - differenceX;

    while (x <= to.x) {
      _bitmap.setPixel(x, y, const ColorRGBA(0, 0, 0, 255));
      ++x;

      if (p < 0) {
        p += 2 * differenceY;

        if (differenceY > differenceX) {
          x += xi;
        }
      } else {
        p = p + 2 * differenceY - 2 * differenceX;
        y += yi;
      }
    }
  }

  void _drawLineX(Point from, Point to) {
    var x = from.x;
    var y = from.y;

    var differenceX = to.x - x;
    final differenceY = to.y - y;

    var xi = 1;

    if (differenceX <= 0) {
      differenceX = -differenceX;
      xi = -1;
    }

    var p = 2 * differenceX - differenceY;

    while (y <= to.y) {
      _bitmap.setPixel(x, y, const ColorRGBA(0, 0, 0, 255));
      y++;

      if (p < 0) {
        p += 2 * differenceX;
      } else {
        p = p + 2 * differenceX - 2 * differenceY;
        x += xi;
      }
    }
  }

  void compute(Point p1, Point p2) {
    final x = p1.x;
    final y = p1.y;

    final differenceX = p2.x - x;
    final differenceY = p2.y - y;

    if (differenceY <= differenceX) {
      if (differenceY.abs() > differenceX) {
        _drawLineX(p2, p1);
      } else {
        _drawLineY(p1, p2);
      }
    } else {
      if (differenceX.abs() > differenceY) {
        _drawLineY(p2, p1);
      } else {
        _drawLineX(p1, p2);
      }
    }
  }
}

typedef Point = ({int x, int y});

abstract class Tool {
  final Bitmap _bitmap;

  Tool(this._bitmap);

  void onTouchDown(Point point);
  void onTouchMove(Point point);
  void onTouchUp();
}

abstract class DrawingTool extends Tool {
  Point? lastPoint;

  DrawingTool(super._bitmap);

  @override
  void onTouchDown(Point point) {
    drawPath(point, point);
    lastPoint = point;
  }

  @override
  void onTouchMove(Point point) {
    drawPath(lastPoint ?? point, point);
    lastPoint = point;
  }

  @override
  void onTouchUp() {
    lastPoint = null;
  }

  void drawPath(Point start, Point end);
}

class PencilTool extends DrawingTool {
  late final LineAlgorithm _lineAlgorithm;

  PencilTool(super._bitmap) : _lineAlgorithm = LineAlgorithm(_bitmap);

  @override
  void drawPath(Point start, Point end) {
    if (start == end) {
      _bitmap.setPixel(
        start.x,
        start.y,
        const ColorRGBA(0, 0, 0, 255),
      );
    } else {
      _lineAlgorithm.compute(start, end);
    }
  }
}

class ToolBar extends StatelessWidget {
  const ToolBar({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.green,
      child: Row(
        children: [
          ToolButton(
            icon: Icons.draw,
            onPressed: () { },
            isSelected: true,
          ),
          ToolButton(
            icon: Icons.phonelink_erase_outlined,
            onPressed: () { },
            isSelected: false,
          ),
        ],
      ),
    );
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
    _zoomAnimationController.stop(canceled: true);
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
    if (_zoomAnimationController.isAnimating) {
      logger.info("STOPPPPED");
      _zoomAnimationController.stop(canceled: true);
    }

    _initialScale = _scale;

    final shouldReset = _initialScale >= 5.0;
    final newScale = shouldReset ? 1.0 : 5.0;

    _scale = newScale;

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
    final scaleRatio = (newScale) / _initialScale;
    final newCenter = tapOrigin - (centerDelta * scaleRatio);

    _initialPanOffset = _panOffset;

    final newPanOffset = shouldReset
        ? const Offset(0, 0)
        : (_initialPanOffset * newScale) + (newCenter - center);

    logger.info(newPanOffset);

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
      body: const Column(
        children: [
          Expanded(
            child: DrawingSurface(),
          ),
          ToolBar(),
        ],
      ),
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
  late final PencilTool _pencilTool;

  @override
  void initState() {
    super.initState();

    _bitmap = Bitmap(100, 100);
    _updateImage();
    _pencilTool = PencilTool(_bitmap);
  }

  Future<void> _updateImage() async {
    final updatedImage = await _bitmap.toImage();

    setState(() {
      _image = updatedImage;
    });
  }

  Point _convertLocalToBitmapCoordinates(Offset localPosition) {
    final artboardPosition = localPosition - _artboardRect.topLeft;

    final x = ((artboardPosition.dx / _artboardRect.width) * _bitmap.width).toInt();
    final y = ((artboardPosition.dy / _artboardRect.height) * _bitmap.height).toInt();

    return (x: x, y: y);
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
                    Point point = _convertLocalToBitmapCoordinates(details.localPosition);
                    _pencilTool.onTouchDown(point);
                    _updateImage();
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
                    Point point = _convertLocalToBitmapCoordinates(details.localFocalPoint);
                    _pencilTool.onTouchMove(point);
                    _updateImage();
                  }
                },
                onScaleEnd: (details) {
                  if (_moveMode) {
                    controller.onScaleEnd(details);
                  } else {
                    _pencilTool.onTouchUp();
                  }
                },
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
    if (x >= 0 && x < width && y >= 0 && y < height) {
      final index = _getIndex(x, y);

      _pixels[index] = color.r;
      _pixels[index + 1] = color.g;
      _pixels[index + 2] = color.b;
      _pixels[index + 3] = color.a;
    }
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
