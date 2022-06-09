package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.extendedOnToolLongTapped(toolName: String) {
    when (toolName) {
        StringConstants.Identifiers.PencilToolIdentifier -> {
            if (viewModel.currentTool == Tool.PencilTool) {
                showSimpleInfoDialog(
                    getString(R.string.pencil_tool_info_title_in_code_str),
                    getString(R.string.pencil_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.FillToolIdentifier  -> {
            if (viewModel.currentTool == Tool.FillTool) {
                showSimpleInfoDialog(
                    getString(R.string.fill_tool_info_title_in_code_str),
                    getString(R.string.fill_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.LineToolIdentifier -> {
            if (viewModel.currentTool == Tool.LineTool) {
                showSimpleInfoDialog(
                    getString(R.string.line_tool_info_title_in_code_str),
                    getString(R.string.line_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.RectangleToolIdentifier -> {
            if (viewModel.currentTool == Tool.RectangleTool) {
                showSimpleInfoDialog(
                    getString(R.string.rectangle_tool_info_title_in_code_str),
                    getString(R.string.rectangle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedRectangleToolIdentifier -> {
            if (viewModel.currentTool == Tool.OutlinedRectangleTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_rectangle_tool_info_title_in_code_str),
                    getString(R.string.outlined_rectangle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.SquareToolIdentifier -> {
            if (viewModel.currentTool == Tool.SquareTool) {
                showSimpleInfoDialog(
                    getString(R.string.square_tool_info_title_in_code_str),
                    getString(R.string.square_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedSquareToolIdentifier -> {
            if (viewModel.currentTool == Tool.OutlinedSquareTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_square_tool_info_title_in_code_str),
                    getString(R.string.outlined_square_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.EllipseToolIdentifier -> {
            if (viewModel.currentTool == Tool.EllipseTool) {
                showSimpleInfoDialog(
                    getString(R.string.ellipse_tool_info_title_in_code_str),
                    getString(R.string.ellipse_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedEllipseToolIdentifier -> {
            if (viewModel.currentTool == Tool.OutlinedEllipseTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_ellipse_tool_info_title_in_code_str),
                    getString(R.string.outlined_ellipse_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.CircleToolIdentifier -> {
            if (viewModel.currentTool == Tool.CircleTool) {
                showSimpleInfoDialog(
                    getString(R.string.circle_tool_info_title_in_code_str),
                    getString(R.string.circle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.OutlinedCircleToolIdentifier -> {
            if (viewModel.currentTool == Tool.OutlinedCircleTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_circle_tool_info_title_in_code_str),
                    getString(R.string.outlined_circle_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.SprayToolIdentifier -> {
            if (viewModel.currentTool == Tool.SprayTool) {
                showSimpleInfoDialog(
                    getString(R.string.spray_tool_info_title_in_code_str),
                    getString(R.string.spray_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.PolygonToolIdentifier -> {
            if (viewModel.currentTool == Tool.PolygonTool) {
                showSimpleInfoDialog(
                    getString(R.string.polygon_tool_info_title_in_code_str),
                    getString(R.string.polygon_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.DitherToolIdentifier -> {
            if (viewModel.currentTool == Tool.DitherTool) {
                showSimpleInfoDialog(
                    getString(R.string.dither_tool_info_title_in_code_str),
                    getString(R.string.dither_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.ShadingToolIdentifier  -> {
            if (viewModel.currentTool == Tool.ShadingTool) {
                showSimpleInfoDialog(
                    getString(R.string.shading_tool_info_title_in_code_str),
                    getString(R.string.shading_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.ColorPickerToolIdentifier -> {
            if (viewModel.currentTool == Tool.ColorPickerTool) {
                showSimpleInfoDialog(
                    getString(R.string.color_picker_tool_info_title_in_code_str),
                    getString(R.string.color_picker_tool_info_text_in_code_str))
            }
        }

        StringConstants.Identifiers.EraseToolIdentifier -> {
            if (viewModel.currentTool == Tool.EraseTool) {
                showSimpleInfoDialog(
                    getString(R.string.erase_tool_info_title_in_code_str),
                    getString(R.string.erase_tool_info_text_in_code_str))
            }
        }
    }
}