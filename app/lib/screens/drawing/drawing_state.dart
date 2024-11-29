import 'package:app/models/tool.dart';
import 'package:app/models/tool_type.dart';
import 'package:flutter/material.dart';
import 'package:graphics/graphics.dart';

class DrawingState with ChangeNotifier {
  var _selectedTool = ToolType.pencil;
  late Tool _selectedToolInstance;
  var _selectedColor = GColors.black;

  late final NodeGraph _nodeGraph;
  late final LayerManager layerManager;
  var _layers = <Layer>[];
  var _activeLayerIndex = 1;

  final int canvasWidth;
  final int canvasHeight;

  ToolType get selectedTool => _selectedTool;
  Tool get selectedToolInstance => _selectedToolInstance;
  GColor get selectedColor => _selectedColor;

  NodeGraph get nodeGraph => _nodeGraph;
  List<Layer> get layers => _layers;
  int get activeLayerIndex => _activeLayerIndex;

  set selectedTool(ToolType tool) {
    _selectedTool = tool;
    _updateSelectedToolInstance();
    notifyListeners();
  }

  set selectedColor(GColor color) {
    _selectedColor = color;
    notifyListeners();
  }

  set activeLayerIndex(int index) {
    _activeLayerIndex = index;
    notifyListeners();
  }

  DrawingState({required this.canvasWidth, required this.canvasHeight}) {
    _initializeNodeGraph();
    _updateSelectedToolInstance();
  }

  void _updateSelectedToolInstance() {
    _selectedToolInstance = _selectedTool.getToolInstance(this);
  }

  void _updateLayers() {
    _layers = layerManager.layers;
    notifyListeners();
  }

  void addLayer() {
    layerManager.addLayer(
      SourceNode(
        source: GBitmap(canvasWidth, canvasHeight, config: GBitmapConfig.rgba)
      ),
    );
    _updateLayers();
  }

  void _initializeNodeGraph() {
    final bitmap =
        GBitmap(canvasWidth, canvasHeight, config: GBitmapConfig.rgba);
    bitmap.fillColor(GColors.green);

    final rootNode = SourceNode(source: bitmap);
    _nodeGraph = NodeGraph(rootNode);

    final layer2Bitmap =
        GBitmap(canvasWidth, canvasHeight, config: GBitmapConfig.rgba);
    drawLine(
      layer2Bitmap,
      (x: 0, y: 0),
      (x: (canvasWidth / 2).floor(), y: (canvasHeight / 2).floor()),
      GColors.black,
    );

    layerManager = LayerManager(_nodeGraph);
    layerManager.addLayer(SourceNode(source: layer2Bitmap));

    _updateLayers();
  }
}
