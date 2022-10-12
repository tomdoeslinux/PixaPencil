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

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.cindx
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.polygonCoordinates
import com.therealbluepandabear.pixapencil.activities.canvas.tooltips.showDitherToolTip
import com.therealbluepandabear.pixapencil.activities.canvas.tooltips.showShadingToolTip
import com.therealbluepandabear.pixapencil.activities.canvas.tooltips.showSprayToolTip
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.dithertoolsettings.DitherToolSettingsFragment
import com.therealbluepandabear.pixapencil.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.therealbluepandabear.pixapencil.utility.constants.Flags
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    if (viewModel.currentTool == Tool.ShadingTool && toolName != StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER) {
        binding.activityCanvasDrawingView.shadingMode = false
    }

    if (viewModel.currentTool == Tool.PolygonTool && toolName != StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER) {
        Flags.DisableActionMove = false
        polygonCoordinates.clear()
        cindx = 0
    }

    val showSprayToolTip = (
                    toolName == StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER &&
                            viewModel.currentTool != Tool.SprayTool &&
                            sharedPreferenceShowSprayToolTip
    )

    if (showSprayToolTip) {
        showSprayToolTip()
    }

    val showShadingToolTip = (
            toolName == StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER &&
                    viewModel.currentTool != Tool.ShadingTool &&
                    sharedPreferenceShowShadingToolTip
            )

    if (showShadingToolTip) {
        showShadingToolTip()
    }

    val showDitherToolTip = (
            toolName == StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER &&
                    viewModel.currentTool != Tool.DitherTool &&
                    sharedPreferenceShowDitherToolTip
            )

    if (showDitherToolTip) {
        showDitherToolTip()
    }

    if (toolName == StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER && viewModel.currentTool == Tool.SprayTool) {
        if (viewModel.currentTool == Tool.SprayTool) {
            supportFragmentManager.commit {
                replace(R.id.activityCanvas_primaryFragmentHost, SprayToolSettingsFragment.newInstance(sharedPreferenceObject))
                addToBackStack(null)
            }
        }
    }

    if (toolName == StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER && viewModel.currentTool == Tool.DitherTool) {
        if (viewModel.currentTool == Tool.DitherTool) {
            supportFragmentManager.commit {
                replace(R.id.activityCanvas_primaryFragmentHost, DitherToolSettingsFragment.newInstance())
                addToBackStack(null)
            }
        }
    }

    if (toolName == StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER && viewModel.currentTool == Tool.ShadingTool) {
        val snackbarText: String = if (shadingToolMode == StringConstants.ShadingToolModes.LIGHTEN_SHADING_TOOL_MODE) {
            getString(R.string.generic_darken_mode_tooltip).also {
                shadingToolMode = StringConstants.ShadingToolModes.DARKEN_SHADING_TOOL_MODE
            }
        } else {
            getString(R.string.generic_lighten_mode_tooltip).also {
                shadingToolMode = StringConstants.ShadingToolModes.LIGHTEN_SHADING_TOOL_MODE
            }
        }

        binding.activityCanvasCoordinatorLayout.showSnackbar(snackbarText, SnackbarDuration.Short)
    }

    if (toolName == StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER && viewModel.currentTool == Tool.PolygonTool) {
        viewModel.currentBitmapAction = null

        polygonCoordinates.clear()
        cindx = 0
    }

    binding.activityCanvasDrawingView.moveMode = toolName == StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER

    viewModel.currentTool = Tool.values().firstOrNull {
        it.toolName == toolName
    } ?: Tool.PencilTool
}