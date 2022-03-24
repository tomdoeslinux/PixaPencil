package com.realtomjoney.pyxlmoose.enums

enum class Tools(val toolFamily: ToolFamily, val draws: Boolean = false, val outlined: Boolean? = null) {
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
        false,
    ),

    OutlinedRectangleTool(
        ToolFamily.Rectangle,
        true,
        true,
    ),

    SquareTool(
        ToolFamily.Rectangle,
        true,
        false,
    ),

    OutlinedSquareTool(
        ToolFamily.Rectangle,
        true,
        true,
    ),

    CircleTool(
        ToolFamily.Circle,
        true,
        false,
    ),

    OutlinedCircleTool(
        ToolFamily.Circle,
        true,
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