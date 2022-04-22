package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnToolLongTapped(toolName: String) {
    when (toolName) {
        StringConstants.Identifiers.PencilToolIdentifier -> {
            if (currentTool == Tools.PencilTool) {
                showSimpleInfoDialog(
                    getString(R.string.pencil_tool_info_title_in_code_str),
                    getString(R.string.pencil_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.FillToolIdentifier  -> {
            if (currentTool == Tools.FillTool) {
                showSimpleInfoDialog(
                    getString(R.string.fill_tool_info_title_in_code_str),
                    getString(R.string.fill_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.LineToolIdentifier -> {
            if (currentTool == Tools.LineTool) {
                showSimpleInfoDialog(
                    getString(R.string.line_tool_info_title_in_code_str),
                    getString(R.string.line_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.RectangleToolIdentifier -> {
            if (currentTool == Tools.RectangleTool) {
                showSimpleInfoDialog(
                    getString(R.string.rectangle_tool_info_title_in_code_str),
                    getString(R.string.rectangle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedRectangleToolIdentifier -> {
            if (currentTool == Tools.OutlinedRectangleTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_rectangle_tool_info_title_in_code_str),
                    getString(R.string.outlined_rectangle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.SquareToolIdentifier -> {
            if (currentTool == Tools.SquareTool) {
                showSimpleInfoDialog(
                    getString(R.string.square_tool_info_title_in_code_str),
                    getString(R.string.square_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedSquareToolIdentifier -> {
            if (currentTool == Tools.OutlinedSquareTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_square_tool_info_title_in_code_str),
                    getString(R.string.outlined_square_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.CircleToolIdentifier -> {
            if (currentTool == Tools.CircleTool) {
                showSimpleInfoDialog(
                    getString(R.string.circle_tool_info_title_in_code_str),
                    getString(R.string.circle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedCircleToolIdentifier -> {
            if (currentTool == Tools.OutlinedCircleTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_circle_tool_info_title_in_code_str),
                    getString(R.string.outlined_circle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.SprayToolIdentifier -> {
            if (currentTool == Tools.SprayTool) {
                showSimpleInfoDialog(
                    getString(R.string.spray_tool_info_title_in_code_str),
                    getString(R.string.spray_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.PolygonToolIdentifier -> {
            if (currentTool == Tools.PolygonTool) {
                showSimpleInfoDialog(
                    getString(R.string.polygon_tool_info_title_in_code_str),
                    getString(R.string.polygon_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.DitherToolIdentifier -> {
            if (currentTool == Tools.DitherTool) {
                showSimpleInfoDialog(
                    getString(R.string.dither_tool_info_title_in_code_str),
                    getString(R.string.dither_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.ShadingToolIdentifier  -> {
            if (currentTool == Tools.ShadingTool) {
                showSimpleInfoDialog(
                    getString(R.string.darken_tool_info_title_in_code_str),
                    getString(R.string.darken_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.ColorPickerToolIdentifier -> {
            if (currentTool == Tools.ColorPickerTool) {
                showSimpleInfoDialog(
                    getString(R.string.color_picker_tool_info_title_in_code_str),
                    getString(R.string.color_picker_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.EraseToolIdentifier -> {
            if (currentTool == Tools.EraseTool) {
                showSimpleInfoDialog(
                    getString(R.string.erase_tool_info_title_in_code_str),
                    getString(R.string.erase_tool_info_text_in_code_str))
            }
        }
    }
}