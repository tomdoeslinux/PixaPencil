import 'package:flutter/material.dart';
import '../../models/tool_type.dart';
import 'widgets/color_picker.dart';
import 'widgets/drawing_canvas.dart';
import 'widgets/tool_panel.dart';
import 'package:graphics/src/core/color.dart' as g;

class DrawingScreen extends StatefulWidget {
  const DrawingScreen({super.key});

  @override
  State<DrawingScreen> createState() => _DrawingScreenState();
}

class _DrawingScreenState extends State<DrawingScreen> {
  var _selectedTool = ToolType.pencil;
  var _selectedColor = g.Colors.black;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ColorPicker(
          onColorSelected: (color) {
            setState(() {
              _selectedColor = color;
            });
          },
        ),
        Expanded(
          child: DrawingCanvas(
            tool: _selectedTool,
            color: _selectedColor,
          ),
        ),
        ToolPanel(
          selectedTool: _selectedTool,
          onToolSelected: (tool) {
            setState(() {
              _selectedTool = tool;
            });
          },
        ),
      ],
    );
  }
}
