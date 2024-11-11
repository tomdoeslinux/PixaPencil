import 'package:app/models/tool_type.dart';
import 'package:flutter/material.dart';
import 'package:graphics/graphics.dart';

class DrawingState with ChangeNotifier {
  var _selectedTool = ToolType.pencil;
  var _selectedColor = GColors.black;

  late NodeGraph _nodeGraph;
  late LayerManager _layerManager;
  var _layers = <Layer>[];

  ToolType get selectedTool => _selectedTool;
  GColor get selectedColor => _selectedColor;

  NodeGraph get nodeGraph => _nodeGraph;
  List<Layer> get layers => _layers;

  set selectedTool(ToolType tool) {
    _selectedTool = tool;
    notifyListeners();
  }

  set selectedColor(GColor color) {
    _selectedColor = color;
    notifyListeners();
  }

  DrawingState() {
    _initializeNodeGraph();
  }

  void _updateLayers() {
    _layers = _layerManager.layers;
    notifyListeners();
  }

  void _initializeNodeGraph() {
    final bitmap = GBitmap(200, 200, config: GBitmapConfig.rgba);
    bitmap.fillColor(GColors.green);

    final rootNode = SourceNode(source: bitmap);
    _nodeGraph = NodeGraph(rootNode);

    final layer2Bitmap = GBitmap(200, 200, config: GBitmapConfig.rgba);
    drawLine(layer2Bitmap, (x: 0, y: 0), (x: 100, y: 100), GColors.black);

    _layerManager = LayerManager(_nodeGraph);
    _layerManager.addLayer(SourceNode(source: layer2Bitmap));

    _updateLayers();
  }
}
