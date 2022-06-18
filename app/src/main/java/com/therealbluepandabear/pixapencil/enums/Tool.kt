package com.therealbluepandabear.pixapencil.enums

import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

enum class Tool(val toolName: String, val toolFamily: ToolFamily, val outlined: Boolean? = null) {
    PencilTool(
        StringConstants.Identifiers.PencilToolIdentifier,
        ToolFamily.None,
        true,
    ),

    EraseTool(
        StringConstants.Identifiers.EraseToolIdentifier,
        ToolFamily.None,
        true,
    ),

    MoveTool(
        StringConstants.Identifiers.MoveToolIdentifier,
        ToolFamily.None,
        false,
    ),

    ColorPickerTool(
        StringConstants.Identifiers.ColorPickerToolIdentifier,
        ToolFamily.None,
        false,
    ),

    FillTool(
        StringConstants.Identifiers.FillToolIdentifier,
        ToolFamily.None,
        true,
    ),

    LineTool(
        StringConstants.Identifiers.LineToolIdentifier,
        ToolFamily.None,
        true,
    ),

    RectangleTool(
        StringConstants.Identifiers.RectangleToolIdentifier,
        ToolFamily.Rectangle,
        true,
    ),

    OutlinedRectangleTool(
        StringConstants.Identifiers.OutlinedRectangleToolIdentifier,
        ToolFamily.Rectangle,
        true,
    ),

    SquareTool(
        StringConstants.Identifiers.SquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
    ),

    OutlinedSquareTool(
        StringConstants.Identifiers.OutlinedSquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
    ),

    EllipseTool(
        StringConstants.Identifiers.EllipseToolIdentifier,
        ToolFamily.Ellipse,
        true,
    ),

    OutlinedEllipseTool(
        StringConstants.Identifiers.OutlinedEllipseToolIdentifier,
        ToolFamily.Ellipse,
        true,
    ),

    CircleTool(
        StringConstants.Identifiers.CircleToolIdentifier,
        ToolFamily.Ellipse,
        true,
    ),

    OutlinedCircleTool(
        StringConstants.Identifiers.OutlinedCircleToolIdentifier,
        ToolFamily.Ellipse,
        true,
    ),

    SprayTool(
        StringConstants.Identifiers.SprayToolIdentifier,
        ToolFamily.None,
        true,
    ),

    PolygonTool(
        StringConstants.Identifiers.PolygonToolIdentifier,
        ToolFamily.None,
        true,
    ),

    DitherTool(
        StringConstants.Identifiers.DitherToolIdentifier,
        ToolFamily.None,
        true,
    ),

    ShadingTool(
        StringConstants.Identifiers.ShadingToolIdentifier,
        ToolFamily.Shader,
        true,
    );

    companion object {
        val defaultTool: Tool = PencilTool
    }
}