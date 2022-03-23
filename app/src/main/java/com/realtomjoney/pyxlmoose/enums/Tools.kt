package com.realtomjoney.pyxlmoose.enums

enum class Tools(val toolFamily: ToolFamily, val draws: Boolean = false) {
    PencilTool(
        ToolFamily.None,
        true,
    ),

    FillTool(
        ToolFamily.None,
        true,
    ),

    HorizontalMirrorTool(
        ToolFamily.Mirror,
        true,
    ),

    VerticalMirrorTool(
        ToolFamily.Mirror,
        true,
    ),

    LineTool(
        ToolFamily.None,
        true,
    ),

    RectangleTool(
        ToolFamily.Rectangle,
        true,
    ),

    OutlinedRectangleTool(
        ToolFamily.Rectangle,
        true,
    ),

    SquareTool(
        ToolFamily.Rectangle,
        true,
    ),

    OutlinedSquareTool(
        ToolFamily.Rectangle,
        true,
    ),

    CircleTool(
        ToolFamily.Circle,
        true,
    ),

    OutlinedCircleTool(
        ToolFamily.Circle,
        true,
    ),

    SprayTool(
        ToolFamily.None,
        true,
    ),

    PolygonTool(
        ToolFamily.None,
        true,
    ),

    DitherTool(
        ToolFamily.None,
        true,
    ),

    DarkenTool(
        ToolFamily.Shader,
        false,
    ),

    LightenTool(
        ToolFamily.Shader,
        false,
    ),

    ColorPickerTool(
        ToolFamily.None,
        false,
    ),

    EraseTool(
        ToolFamily.None,
        true,
    ),
}