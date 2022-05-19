package com.therealbluepandabear.pixapencil.enums

import com.therealbluepandabear.pixapencil.utility.StringConstants

enum class Tools(val toolName: String, val toolFamily: ToolFamily, val draws: Boolean = false, val outlined: Boolean? = null) {
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
        false,
    ),

    OutlinedRectangleTool(
        StringConstants.Identifiers.OutlinedRectangleToolIdentifier,
        ToolFamily.Rectangle,
        true,
        true,
    ),

    SquareTool(
        StringConstants.Identifiers.SquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
        false,
    ),

    OutlinedSquareTool(
        StringConstants.Identifiers.OutlinedSquareToolIdentifier,
        ToolFamily.Rectangle,
        true,
        true,
    ),

    CircleTool(
        StringConstants.Identifiers.CircleToolIdentifier,
        ToolFamily.Circle,
        true,
        false,
    ),

    OutlinedCircleTool(
        StringConstants.Identifiers.OutlinedCircleToolIdentifier,
        ToolFamily.Circle,
        true,
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
        val defaultTool: Tools = PencilTool

        fun findToolByName(name: String): Tools? {
            return values().find {
                it.toolName == name
            }
        }
    }
}