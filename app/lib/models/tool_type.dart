import 'package:flutter/material.dart';
import 'package:graphics/src/pipeline/node_graph.dart';

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