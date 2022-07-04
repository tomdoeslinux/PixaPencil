package com.therealbluepandabear.pixapencil.enums

import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

enum class Tool(val toolName: String, val toolFamily: ToolFamily, val outlined: Boolean? = null) {
    PencilTool(
        StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    EraseTool(
        StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    MoveTool(
        StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    ColorPickerTool(
        StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    FillTool(
        StringConstants.Identifiers.FILL_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    LineTool(
        StringConstants.Identifiers.LINE_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    RectangleTool(
        StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER,
        ToolFamily.Rectangle,
        false,
    ),

    OutlinedRectangleTool(
        StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER,
        ToolFamily.Rectangle,
        true,
    ),

    SquareTool(
        StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER,
        ToolFamily.Rectangle,
        false,
    ),

    OutlinedSquareTool(
        StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER,
        ToolFamily.Rectangle,
        true,
    ),

    EllipseTool(
        StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER,
        ToolFamily.Ellipse,
        false,
    ),

    OutlinedEllipseTool(
        StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER,
        ToolFamily.Ellipse,
        true,
    ),

    CircleTool(
        StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER,
        ToolFamily.Ellipse,
        false,
    ),

    OutlinedCircleTool(
        StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER,
        ToolFamily.Ellipse,
        true,
    ),

    SprayTool(
        StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    PolygonTool(
        StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    DitherTool(
        StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER,
        ToolFamily.None,
    ),

    ShadingTool(
        StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER,
        ToolFamily.Shader,
    );

    companion object {
        val defaultTool: Tool = PencilTool
    }
}