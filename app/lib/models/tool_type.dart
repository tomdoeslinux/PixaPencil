import 'package:graphics/graphics.dart';

import 'pencil_tool.dart';
import 'tool.dart';

enum ToolType {
  pencil;

  Tool getToolInstance(NodeGraph nodeGraph) {
    switch (this) {
      case ToolType.pencil:
        return PencilTool(nodeGraph);
    }
  }
}