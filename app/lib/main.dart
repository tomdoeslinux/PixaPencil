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
    return ChangeNotifierProvider(
      create: ( context) => ToolNotifier(),
      child: MaterialApp(
        title: "PixaPencil",
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        home: const MyHomePage(),
      ),
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


void _drawLineY(Bitmap bitmap, Point from, Point to, ColorRGBA color) {
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
    bitmap.setPixel(x, y, color);
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

void _drawLineX(Bitmap bitmap, Point from, Point to, ColorRGBA color) {
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
    bitmap.setPixel(x, y, color);
    y++;

    if (p < 0) {
      p += 2 * differenceX;
    } else {
      p = p + 2 * differenceX - 2 * differenceY;
      x += xi;
    }
  }
}

void drawLine(Bitmap bitmap, Point from, Point to, ColorRGBA color) {
  final x = from.x;
  final y = from.y;

  final differenceX = to.x - x;
  final differenceY = to.y - y;

  if (differenceY <= differenceX) {
    if (differenceY.abs() > differenceX) {
      _drawLineX(bitmap, to, from, color);
    } else {
      _drawLineY(bitmap, from, to, color);
    }
  } else {
    if (differenceX.abs() > differenceY) {
      _drawLineY(bitmap, to, from, color);
    } else {
      _drawLineX(bitmap, from, to, color);
    }
  }
}

typedef Vector = (double x, double y);

extension VectorUtils on Vector {
  double get x => $1;
  double get y => $2;

  @pragma('vm:prefer-inline')
  Vector subtract(Vector v1) {
    return (x - v1.x, y - v1.y);
  }

  @pragma('vm:prefer-inline')
  Vector add(Vector v1) {
    return (x + v1.x, y + v1.y);
  }

  @pragma('vm:prefer-inline')
  double dot(Vector v2) {
    return x * v2.x + y * v2.y;
  }

  @pragma('vm:prefer-inline')
  double magnitude() {
    return sqrt(x * x + y * y);
  }

  @pragma('vm:prefer-inline')
  Vector normalize() {
    final mag = magnitude();

    if (mag == 0) {
      return (0, 0);
    }

    return (x / mag, y / mag);
  }
}
class BitmapIterator {
  final Bitmap _bitmap;

  BitmapIterator(this._bitmap);

  int _x = 0;
  int _y = 0;

  int get x => _x;
  int get y => _y;

  @pragma('vm:prefer-inline')
  int get currentPixelIndex => (_y * _bitmap.width + _x) * Bitmap.rgbaByteCount;

  @pragma('vm:prefer-inline')
  int get currentPixel => _bitmap.getPixel(x, y).toHex();

  @pragma('vm:prefer-inline')
  void put(ColorRGBA color) {
    _bitmap.setPixel(x, y, color);
  }

  @pragma("vm:prefer-inline")
  ColorRGBA getPixel(int x, int y) => _bitmap.getPixel(x, y);

  void reset() {
    _x = 0;
    _y = 0;
  }

  @pragma('vm:prefer-inline')
  bool moveNext() {
    // Move the iterator through the bitmap.
    // - Start by moving rightwards along the x-axis.
    // - Once the iterator reaches the final x position in the row,
    // - ...move downwards to the next row on the y-axis and reset the x position to 0.
    // - This continues until the entire bitmap is processed...

    if (_x < _bitmap.width - 1) {
      ++_x;
    } else if (_y < _bitmap.height - 1) {
      _x = 0;
      ++_y;
    } else {
      return false;
    }

    return true;
  }

  @pragma('vm:prefer-inline')
  bool moveHorizontal([int steps = 1]) {
    final newX = _x + steps;

    if (newX < _bitmap.width) {
      _x = newX;
    } else if (_y < _bitmap.height - 1) {
      _x = 0;
      ++_y;
    } else {
      return false;
    }

    return true;
  }

  @pragma('vm:prefer-inline')
  bool moveVertical([int steps = 1]) {
    final newY = _y + steps;

    if (newY < _bitmap.height) {
      _y = newY;
    } else if (_x < _bitmap.width - 1) {
      _y = 0;
      ++_x;
    } else {
      return false;
    }

    return true;
  }

  @pragma('vm:prefer-inline')
  void moveTo(int x, int y) {
    _x = x;
    _y = y;
  }
}

@pragma('vm:prefer-inline')
int interpolateColors(int c0, int c1, double factor) {
  final c0r = (c0 >> 24) & 0xFF;
  final c0g = (c0 >> 16) & 0xFF;
  final c0b = (c0 >> 8) & 0xFF;
  final c0a = c0 & 0xFF;

  final c1r = (c1 >> 24) & 0xFF;
  final c1g = (c1 >> 16) & 0xFF;
  final c1b = (c1 >> 8) & 0xFF;
  final c1a = c1 & 0xFF;

  final r = (c0r + factor * (c1r - c0r)).round();
  final g = (c0g + factor * (c1g - c0g)).round();
  final b = (c0b + factor * (c1b - c0b)).round();
  final a = (c0a + factor * (c1a - c0a)).round();

  return (r << 24) | (g << 16) | (b << 8) | a;
}

void drawLinearGradient(Bitmap source, int fromX, int fromY, int toX, int toY, ColorRGBA fromColor, ColorRGBA toColor) {
  if (fromColor == toColor) {
    // clear
  }

  final Vector gradientStart = (fromX.toDouble(), fromY.toDouble());
  final Vector gradientEnd = (toX.toDouble(), toY.toDouble());

  final gradientLine = gradientEnd.subtract(gradientStart);
  final gradientLineMagnitude = gradientLine.magnitude();

  final gradientLineNorm = gradientLine.normalize();

  if (fromColor.a == 0 && toColor.a != 0) {
    fromColor = ColorRGBA(fromColor.r, fromColor.g, fromColor.b, 0);
  } else if (fromColor.a != 0 && toColor.a == 0) {
    toColor = ColorRGBA(toColor.r, toColor.g, toColor.b, 0);
  }

  final sourceIterator = BitmapIterator(source);

  const steps = 256;
  final colorLUT = List<int>.generate(steps, (i) {
    final double factor = i / (steps - 1);
    return interpolateColors(fromColor.toHex(), toColor.toHex(), factor);
  });

  ColorRGBA colorToSet;

  do {
    Vector curPixel = (sourceIterator.x.toDouble(), sourceIterator.y.toDouble());
    curPixel = curPixel.subtract(gradientStart);

    final projFactor = curPixel.dot(gradientLineNorm) / gradientLineMagnitude;

    if (projFactor > 1) {
      colorToSet = toColor;
    } else if (projFactor < 0) {
      colorToSet = fromColor;
    } else {
      final lutIndex = (projFactor * (steps - 1)).clamp(0, steps - 1).toInt();
      colorToSet = ColorRGBA.fromHex(colorLUT[lutIndex]);
    }

    sourceIterator.put(colorToSet);

  } while (sourceIterator.moveNext());
}

enum ToolType {
  pencil(Icons.draw),
  eraser(Icons.phonelink_erase),
  gradient(Icons.gradient);

  Tool getToolInstance(Bitmap bitmap) {
    switch (this) {
      case ToolType.pencil:
        return PencilTool(bitmap);
      case ToolType.eraser:
        return EraserTool(bitmap);
      case ToolType.gradient:
        return GradientTool(bitmap);
    }
  }

  final IconData icon;

  const ToolType(this.icon);
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
  PencilTool(super._bitmap);

  @override
  void drawPath(Point start, Point end) {
    if (start == end) {
      _bitmap.setPixel(
        start.x,
        start.y,
        ColorRGBA.black,
      );
    } else {
      drawLine(_bitmap, start, end, ColorRGBA.black);
    }
  }
}

class EraserTool extends DrawingTool {
  EraserTool(super._bitmap);

  @override
  void drawPath(Point start, Point end) {
    if (start == end) {
      _bitmap.setPixel(
        start.x,
        start.y,
        ColorRGBA.transparent,
      );
    } else {
      drawLine(_bitmap, start, end, ColorRGBA.transparent);
    }
  }
}

class GradientTool extends Tool {
  Logger logger = Logger("GradientTool");
  Point? startPoint;

  GradientTool(super._bitmap);

  @override
  void onTouchDown(Point point) {
    startPoint = point;
  }

  @override
  void onTouchMove(Point point) {
    if (startPoint == null) return;

    logger.info("Drawing gradient");

    drawLinearGradient(
      _bitmap,
      startPoint!.x,
      startPoint!.y,
      point.x,
      point.y,
      ColorRGBA.black,
      const ColorRGBA(255, 0, 0, 255),
    );
  }

  @override
  void onTouchUp() {
    startPoint = null;
  }
}

class ColorSwatchPanel extends StatelessWidget {
  static const List<ColorRGBA> _colors = [
    ColorRGBA(0, 0, 0, 255),
    ColorRGBA(255, 255, 255, 255),
    ColorRGBA(255, 0, 0, 255),
    ColorRGBA(0, 255, 0, 255),
    ColorRGBA(0, 0, 255, 255),
    ColorRGBA(255, 255, 0, 255),
    ColorRGBA(0, 255, 255, 255),
    ColorRGBA(255, 0, 255, 255),
    ColorRGBA(255, 165, 0, 255),
    ColorRGBA(128, 0, 128, 255),
  ];

  const ColorSwatchPanel({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      scrollDirection: Axis.horizontal,
      child: Row(
        children: [
          for (final color in _colors)
            Container(
              height: 50,
              width: 50,
              color: ui.Color.fromARGB(color.a, color.r, color.g, color.b),
            ),
        ],
      ),
    );
  }
}

class ToolNotifier extends ChangeNotifier {
  var _currentToolType = ToolType.pencil;

  ToolType get currentToolType => _currentToolType;

  set currentToolType(ToolType toolType) {
    _currentToolType = toolType;
    notifyListeners();
  }
}

class ToolPanel extends StatelessWidget {
  const ToolPanel({super.key});

  @override
  Widget build(BuildContext context) {
    final toolNotifier = Provider.of<ToolNotifier>(context);
    
    return Container(
      color: Colors.green,
      child: Row(
        children: [
          for (final toolType in ToolType.values) 
            ToolButton(
              icon: toolType.icon,
              onPressed: () => toolNotifier.currentToolType = toolType,
              isSelected: toolNotifier.currentToolType == toolType,
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
          ColorSwatchPanel(),
          ToolPanel(),
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
  Tool? activeTool;
  ToolType? previousToolType;
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    final toolType = Provider.of<ToolNotifier>(context).currentToolType;
    if (toolType != previousToolType) {
      activeTool = toolType.getToolInstance(_bitmap);
      previousToolType = toolType;
    }
  }

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => GestureController(this),
      child: LayoutBuilder(
        builder: (context, constraints) {
          final availableSize = constraints.biggest;
          final center = Offset(
            availableSize.width / 2,
            availableSize.height / 2,
          );
          final gestureController = Provider.of<GestureController>(context);

          _artboardRect = _calculateArtboardRect(
              availableSize,
            gestureController.panOffset,
            gestureController.scale,
          );
      
          return GestureDetector(
            onScaleStart: _moveMode ? gestureController.onScaleStart : null,
            onTapDown: (details) {
              if (!_moveMode) {
                Point point = _convertLocalToBitmapCoordinates(details.localPosition);
                activeTool!.onTouchDown(point);
                _updateImage();
              }
            },
            onDoubleTapDown: (details) {
              if (_moveMode) {
                gestureController.onDoubleTapDown(details, center);
              }
            },
            onScaleUpdate: (details) {
              if (_moveMode) {
                gestureController.onScaleUpdate(details, center);
              } else if (details.scale == 1) {
                Point point = _convertLocalToBitmapCoordinates(details.localFocalPoint);
                activeTool!.onTouchMove(point);
                _updateImage();
              }
            },
            onScaleEnd: (details) {
              if (_moveMode) {
                gestureController.onScaleEnd(details);
              } else {
                activeTool!.onTouchUp();
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

  const ColorRGBA.fromHex(int input)
      : r = (input >> 24) & 0xFF,
        g = (input >> 16) & 0xFF,
        b = (input >> 8) & 0xFF,
        a = input & 0xFF;

  static const ColorRGBA black = ColorRGBA(0, 0, 0, 255);
  static const ColorRGBA transparent = ColorRGBA(0, 0, 0, 0);

  @pragma("vm:prefer-inline")
  int toHex() {
    return (r << 24) | (g << 16) | (b << 8) | a;
  }
}

class Bitmap {
  static const rgbaByteCount = 4;

  final int width;
  final int height;
  final Uint8List _pixels;

  Bitmap(this.width, this.height)
      : _pixels = Uint8List(width * height * rgbaByteCount);

  Bitmap.fromPixels(this._pixels, this.width, this.height);

  ByteBuffer get buffer => _pixels.buffer;

  int _getIndex(int x, int y) {
    return (y * width + x) * rgbaByteCount;
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
