package com.realtomjoney.pyxlmoose.enums

enum class Tools(val toolFamily: ToolFamily) {
    PencilTool(
        ToolFamily.None
    ),

    FillTool(
        ToolFamily.None
    ),

    HorizontalMirrorTool(
        ToolFamily.Mirror
    ),

    VerticalMirrorTool(
        ToolFamily.Mirror
    ),

    LineTool(
        ToolFamily.None
    ),

    RectangleTool(
        ToolFamily.Rectangle
    ),

    OutlinedRectangleTool(
        ToolFamily.Rectangle
    ),

    SquareTool(
        ToolFamily.Rectangle
    ),

    OutlinedSquareTool(
        ToolFamily.Rectangle
    ),

    CircleTool(
        ToolFamily.Circle
    ),

    OutlinedCircleTool(
        ToolFamily.Circle
    ),

    SprayTool(
        ToolFamily.None
    ),

    PolygonTool(
        ToolFamily.None
    ),

    DitherTool(
        ToolFamily.None
    ),

    DarkenTool(
        ToolFamily.Shader
    ),

    LightenTool(
        ToolFamily.Shader
    ),

    ColorPickerTool(
        ToolFamily.None
    ),

    EraseTool(
        ToolFamily.None
    ),
}