import 'package:app/models/tool_type.dart';
import 'package:app/screens/drawing/drawing_state.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class ToolsPanel extends StatelessWidget {
  const ToolsPanel({super.key});

  @override
  Widget build(BuildContext context) {
    final drawingState = Provider.of<DrawingState>(context);

    return Container(
      color: Colors.blue,
      child: Row(
        children: [
          IconButton(
            onPressed: () {
              drawingState.selectedTool = ToolType.pencil;
            },
            icon: const Icon(Icons.brush),
            color: drawingState.selectedTool == ToolType.pencil
                ? Colors.white
                : null,
          ),
        ],
      ),
    );
  }
}
