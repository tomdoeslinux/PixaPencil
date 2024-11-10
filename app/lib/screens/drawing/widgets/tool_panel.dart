import 'package:app/models/tool_type.dart';
import 'package:flutter/material.dart';

class ToolPanel extends StatelessWidget {
  final ToolType selectedTool;
  final ValueChanged<ToolType> onToolSelected;

  const ToolPanel({
    super.key,
    required this.selectedTool,
    required this.onToolSelected,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.blue,
      child: Row(
        children: [
          IconButton(
            onPressed: () {},
            icon: const Icon(Icons.brush),
            color: selectedTool == ToolType.pencil ? Colors.white : null,
          ),
        ],
      ),
    );
  }
}
