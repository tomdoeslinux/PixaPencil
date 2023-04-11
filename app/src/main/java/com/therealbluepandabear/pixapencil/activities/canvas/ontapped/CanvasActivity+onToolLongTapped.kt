/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import androidx.annotation.StringRes
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

data class ToolInfo(
    val identifier: String,
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    val tool: Tool
)

val toolInfos = listOf(
    ToolInfo(
        identifier = StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER,
        titleResId = R.string.pencil_tool_info_title,
        descriptionResId = R.string.pencil_tool_info_text,
        tool = Tool.PencilTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.FILL_TOOL_IDENTIFIER,
        titleResId = R.string.fill_tool_info_title,
        descriptionResId = R.string.fill_tool_info_text,
        tool = Tool.FillTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER,
        titleResId = R.string.move_tool_info_title,
        descriptionResId = R.string.move_tool_info_text,
        tool = Tool.MoveTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.LINE_TOOL_IDENTIFIER,
        titleResId = R.string.line_tool_info_title,
        descriptionResId = R.string.line_tool_info_text,
        tool = Tool.LineTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER,
        titleResId = R.string.rectangle_tool_info_title,
        descriptionResId = R.string.rectangle_tool_info_text,
        tool = Tool.RectangleTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER,
        titleResId = R.string.outlined_rectangle_tool_info_title,
        descriptionResId = R.string.outlined_rectangle_tool_info_text,
        tool = Tool.OutlinedRectangleTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER,
        titleResId = R.string.square_tool_info_title,
        descriptionResId = R.string.square_tool_info_text,
        tool = Tool.SquareTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER,
        titleResId = R.string.outlined_square_tool_info_title,
        descriptionResId = R.string.outlined_square_tool_info_text,
        tool = Tool.OutlinedSquareTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER,
        titleResId = R.string.ellipse_tool_info_title,
        descriptionResId = R.string.ellipse_tool_info_text,
        tool = Tool.EllipseTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER,
        titleResId = R.string.outlined_ellipse_tool_info_title,
        descriptionResId = R.string.outlined_ellipse_tool_info_text,
        tool = Tool.OutlinedEllipseTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER,
        titleResId = R.string.circle_tool_info_title,
        descriptionResId = R.string.circle_tool_info_text,
        tool = Tool.CircleTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER,
        titleResId = R.string.outlined_circle_tool_info_title,
        descriptionResId = R.string.outlined_circle_tool_info_text,
        tool = Tool.OutlinedCircleTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER,
        titleResId = R.string.spray_tool_info_title,
        descriptionResId = R.string.spray_tool_info_text,
        tool = Tool.SprayTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER,
        titleResId = R.string.polygon_tool_info_title,
        descriptionResId = R.string.polygon_tool_info_text,
        tool = Tool.PolygonTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER,
        titleResId = R.string.dither_tool_info_title,
        descriptionResId = R.string.dither_tool_info_text,
        tool = Tool.DitherTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER,
        titleResId = R.string.shading_tool_info_title,
        descriptionResId = R.string.shading_tool_info_text,
        tool = Tool.ShadingTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER,
        titleResId = R.string.color_picker_tool_info_title,
        descriptionResId = R.string.color_picker_tool_info_text,
        tool = Tool.ColorPickerTool
    ),
    ToolInfo(
        identifier = StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER,
        titleResId = R.string.erase_tool_info_title,
        descriptionResId = R.string.erase_tool_info_text,
        tool = Tool.EraseTool
    )
)

fun CanvasActivity.extendedOnToolLongTapped(toolName: String) {
    val toolInfo = toolInfos.find { it.identifier == toolName && it.tool == viewModel.currentTool } ?: return

    showSimpleInfoDialog(getString(toolInfo.titleResId), getString(toolInfo.descriptionResId))
}