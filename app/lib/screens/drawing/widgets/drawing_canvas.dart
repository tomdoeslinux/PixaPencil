import 'dart:async';
import 'dart:math';

import 'package:app/models/pencil_tool.dart';
import 'package:app/models/tool.dart';
import 'package:app/models/tool_type.dart';
import 'package:flutter/material.dart';
import 'package:graphics/src/core/bitmap.dart';
import 'package:graphics/src/pipeline/node_graph.dart';
import 'package:graphics/src/pipeline/source_node.dart';
import 'package:graphics/src/core/rect.dart' as g;
import 'package:graphics/src/core/color.dart' as g;
import 'package:graphics/src/core/point2d.dart';
import 'dart:ui' as ui;

class DrawingCanvas extends StatefulWidget {
  final ToolType tool;
  final g.Color color;

  const DrawingCanvas({
    super.key,
    required this.tool,
    required this.color,
  });

  @override
  State<DrawingCanvas> createState() => _DrawingCanvasState();
}

class _DrawingCanvasState extends State<DrawingCanvas> {
  late Tool _toolInstance;

  late NodeGraph _nodeGraph;
  ui.Image? _nodeOutput;

  var _moveMode = true;
  var _zoom = 1.0;
  var _initialZoom = 1.0;
  var _zoomOrigin = Offset.zero;
  var _panOffset = Offset.zero;

  @override
  void initState() {
    super.initState();

    _initializeNodeGraph();
  }

  void _onScaleStart(ScaleStartDetails details) {
    if (_moveMode) {
      _initialZoom = _zoom;
      _zoomOrigin = details.focalPoint;
    }
  }

  void _onScaleUpdate(ScaleUpdateDetails details, Rect artboardRect) {
    if (_moveMode) {
      if (details.scale == 1) {
        setState(() {
          _panOffset += details.focalPointDelta;
        });
      } else {
        setState(() {
          _zoom = (_initialZoom * details.scale).clamp(0.5, 5.0);
        });
      }
    } else {
      final point = _convertLocalToBitmapCoordinates(
          details.localFocalPoint, artboardRect);
      _toolInstance.onTouchMove(point, widget.color);

      _updateNodeOutput();
    }
  }

  void _onTapDown(TapDownDetails details, Rect artboardRect) {
    final point =
        _convertLocalToBitmapCoordinates(details.localPosition, artboardRect);
    _toolInstance.onTouchDown(point, widget.color);

    _updateNodeOutput();
  }

  Point2D _convertLocalToBitmapCoordinates(
      Offset localPosition, Rect artboardRect) {
    final artboardPosition = localPosition - artboardRect.topLeft;

    final x = ((artboardPosition.dx / artboardRect.width) * 20).toInt();
    final y = ((artboardPosition.dy / artboardRect.height) * 20).toInt();

    return (x: x, y: y);
  }

  void _initializeNodeGraph() {
    final bitmap = Bitmap(20, 20, config: BitmapConfig.rgba);
    bitmap.setPixel(0, 0, g.Colors.black);

    final sourceNode = SourceNode(source: bitmap);
    _nodeGraph = NodeGraph(sourceNode);

    _updateNodeOutput();

    _toolInstance = widget.tool.getToolInstance(_nodeGraph);
  }

  Future<void> _updateNodeOutput() async {
    final nodeOutputBitmap = _nodeGraph.process(
      g.Rect(
        x: 0,
        y: 0,
        width: 20,
        height: 20,
      ),
    );
    final updatedImage = await nodeOutputBitmap.toFlutterImage();

    setState(() {
      _nodeOutput = updatedImage;
    });
  }

  @override
  Widget build(BuildContext context) {
    if (_nodeOutput == null) {
      return const Text("Loading...");
    }

    final canvasPainter = CanvasPainter(
      _nodeOutput!,
      _zoom,
      _zoomOrigin,
      _panOffset,
    );

    return Stack(
      children: [
        ClipRect(
          child: GestureDetector(
            onScaleStart: _onScaleStart,
            onScaleUpdate: (details) {
              _onScaleUpdate(details, canvasPainter.artboardRect);
            },
            onTapDown: (details) {
              _onTapDown(details, canvasPainter.artboardRect);
            },
            child: CustomPaint(
              painter: canvasPainter,
              size: Size.infinite,
            ),
          ),
        ),
        Positioned(
          bottom: 16,
          right: 16,
          child: FloatingActionButton(
            onPressed: () {
              setState(() {
                _moveMode = !_moveMode;
              });
            },
            child: Icon(_moveMode ? Icons.pan_tool : Icons.touch_app),
          ),
        )
      ],
    );
  }
}

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

class CanvasPainter extends CustomPainter {
  final ui.Image _image;
  final double _zoom;
  final Offset _zoomOrigin;
  final Offset _panOffset;

  late Rect _artboardRect;
  final _artboardPaint = Paint()..color = Colors.white;

  CanvasPainter(
    this._image,
    this._zoom,
    this._zoomOrigin,
    this._panOffset,
  );

  Rect get artboardRect => _artboardRect;

  void _calculateArtboardRect(Size canvasSize) {
    final originalWidth = min(
        canvasSize.width, _image.width * (canvasSize.height / _image.height));
    final originalHeight = originalWidth * (_image.height / _image.width);

    final left = (canvasSize.width - originalWidth) / 2 + _panOffset.dx;
    final top = (canvasSize.height - originalHeight) / 2 + _panOffset.dy;

    _artboardRect = Rect.fromLTWH(left - _zoomOrigin.dx, top - _zoomOrigin.dy,
        originalWidth * _zoom, originalHeight * _zoom);
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

    canvas.drawRect(
      Rect.fromLTWH(0, 0, size.width, size.height),
      Paint()..color = const Color(0xFFC0C0C0),
    );
    canvas.drawRect(_artboardRect, _artboardPaint);
    canvas.drawImageRect(_image, _getSrcRect(), _artboardRect, Paint());
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    return true;
  }
}
