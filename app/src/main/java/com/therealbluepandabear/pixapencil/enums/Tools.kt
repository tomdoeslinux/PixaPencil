package com.therealbluepandabear.pixapencil.enums

import com.therealbluepandabear.pixapencil.utility.StringConstants

enum class Tools(val toolName: String, val toolFamily: ToolFamily, val draws: Boolean = false, val outlined: Boolean? = null) {
    PencilTool(
        StringConstants.PencilToolIdentifier,
        ToolFamily.None,
        true,
    ),

    FillTool(
        StringConstants.FillToolIdentifier,
        ToolFamily.None,
        true,
    ),

    LineTool(
        StringConstants.LineToolIdentifier,
        ToolFamily.None,
        true,
    ),

    RectangleTool(
        StringConstants.RectangleToolIdentifier,
        ToolFamily.Rectangle,
        true,
        false,
    ),

    OutlinedRectangleTool(
        StringConstants.OutlinedRectangleToolIdentifier,
        ToolFamily.Rectangle,
        true,
        true,
    ),

    SquareTool(
        StringConstants.SquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
        false,
    ),

    OutlinedSquareTool(
        StringConstants.OutlinedSquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
        true,
    ),

    CircleTool(
        StringConstants.CircleToolIdentifier,
        ToolFamily.Circle,
        true,
        false,
    ),

    OutlinedCircleTool(
        StringConstants.OutlinedCircleToolIdentifier,
        ToolFamily.Circle,
        true,
        true,
    ),

    SprayTool(
        StringConstants.SprayToolIdentifier,
        ToolFamily.None,
        true,
    ),

    PolygonTool(
        StringConstants.PolygonToolIdentifier,
        ToolFamily.None,
        true,
    ),

    DitherTool(
        StringConstants.DitherToolIdentifier,
        ToolFamily.None,
        true,
    ),

    DarkenTool(
        StringConstants.DarkenToolIdentifier,
        ToolFamily.Shader,
        false,
    ),

    LightenTool(
        StringConstants.LightenToolIdentifier,
        ToolFamily.Shader,
        false,
    ),

    ColorPickerTool(
        StringConstants.ColorPickerToolIdentifier,
        ToolFamily.None,
        false,
    ),

    EraseTool(
        StringConstants.EraseToolIdentifier,
        ToolFamily.None,
        true,
    );

    companion object {
        fun findToolByName(name: String): Tools? {
            return values().find { it.toolName == name }
        }
    }
}