package com.therealbluepandabear.pixapencil.enums

import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

enum class Tool(val toolName: String, val toolFamily: ToolFamily, val outlined: Boolean? = null) {
    PencilTool(
        StringConstants.Identifiers.PencilToolIdentifier,
        ToolFamily.None,
    ),

    EraseTool(
        StringConstants.Identifiers.EraseToolIdentifier,
        ToolFamily.None,
    ),

    MoveTool(
        StringConstants.Identifiers.MoveToolIdentifier,
        ToolFamily.None,
    ),

    ColorPickerTool(
        StringConstants.Identifiers.ColorPickerToolIdentifier,
        ToolFamily.None,
    ),

    FillTool(
        StringConstants.Identifiers.FillToolIdentifier,
        ToolFamily.None,
    ),

    LineTool(
        StringConstants.Identifiers.LineToolIdentifier,
        ToolFamily.None,
    ),

    RectangleTool(
        StringConstants.Identifiers.RectangleToolIdentifier,
        ToolFamily.Rectangle,
        false,
    ),

    OutlinedRectangleTool(
        StringConstants.Identifiers.OutlinedRectangleToolIdentifier,
        ToolFamily.Rectangle,
        true,
    ),

    SquareTool(
        StringConstants.Identifiers.SquareToolIdentifier,
        ToolFamily.Rectangle,
        false,
    ),

    OutlinedSquareTool(
        StringConstants.Identifiers.OutlinedSquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
    ),

    EllipseTool(
        StringConstants.Identifiers.EllipseToolIdentifier,
        ToolFamily.Ellipse,
        false,
    ),

    OutlinedEllipseTool(
        StringConstants.Identifiers.OutlinedEllipseToolIdentifier,
        ToolFamily.Ellipse,
        true,
    ),

    CircleTool(
        StringConstants.Identifiers.CircleToolIdentifier,
        ToolFamily.Ellipse,
        false,
    ),

    OutlinedCircleTool(
        StringConstants.Identifiers.OutlinedCircleToolIdentifier,
        ToolFamily.Ellipse,
        true,
    ),

    SprayTool(
        StringConstants.Identifiers.SprayToolIdentifier,
        ToolFamily.None,
    ),

    PolygonTool(
        StringConstants.Identifiers.PolygonToolIdentifier,
        ToolFamily.None,
    ),

    DitherTool(
        StringConstants.Identifiers.DitherToolIdentifier,
        ToolFamily.None,
    ),

    ShadingTool(
        StringConstants.Identifiers.ShadingToolIdentifier,
        ToolFamily.Shader,
    );

    companion object {
        val defaultTool: Tool = PencilTool
    }
}