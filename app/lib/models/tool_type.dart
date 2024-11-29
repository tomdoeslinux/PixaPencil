import 'package:app/screens/drawing/drawing_state.dart';

import 'pencil_tool.dart';
import 'tool.dart';

enum ToolType {
  pencil,
  eraser;

  Tool getToolInstance(DrawingState drawingState) {
    switch (this) {
      case ToolType.pencil:
        return PencilTool(drawingState);
      case ToolType.eraser:
        return PencilTool(drawingState, isEraser: true);
    }
  }
}
