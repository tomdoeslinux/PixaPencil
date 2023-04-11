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

package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinate

var polygonCoordinates = mutableListOf<Coordinate>()
var cindx = 0

fun CanvasActivity.extendedOnPixelTapped(coordinateTapped: Coordinate) {
    if (!primaryAlgorithmInfoParameterInitialized) {
        initPrimaryAlgorithmInfoParameter()
    }

    viewModel.saved = false
    when (viewModel.currentTool) {
        Tool.PencilTool -> {
            pencilToolOnPixelTapped(coordinateTapped)
        }

        Tool.EraseTool -> {
            eraseToolOnPixelTapped(coordinateTapped)
        }

        Tool.ColorPickerTool -> {
            colorPickerToolOnPixelTapped(coordinateTapped)
        }

        Tool.FillTool -> {
            fillToolOnPixelTapped(coordinateTapped)
        }

        Tool.LineTool -> {
            lineToolOnPixelTapped(coordinateTapped)
        }

        Tool.RectangleTool -> {
            rectangleToolOnPixelTapped(coordinateTapped)
        }

        Tool.OutlinedRectangleTool -> {
            rectangleToolOnPixelTapped(coordinateTapped)
        }

        Tool.SquareTool -> {
            rectangleToolOnPixelTapped(coordinateTapped)
        }

        Tool.OutlinedSquareTool -> {
            rectangleToolOnPixelTapped(coordinateTapped)
        }

        Tool.EllipseTool -> {
            ellipseToolOnPixelTapped(coordinateTapped)
        }

        Tool.OutlinedEllipseTool -> {
            ellipseToolOnPixelTapped(coordinateTapped)
        }

        Tool.CircleTool -> {
            circleToolOnPixelTapped(coordinateTapped)
        }

        Tool.OutlinedCircleTool -> {
            circleToolOnPixelTapped(coordinateTapped)
        }

        Tool.SprayTool -> {
            sprayToolOnPixelTapped(coordinateTapped)
        }

        Tool.PolygonTool -> {
            polygonToolOnPixelTapped(coordinateTapped)
        }

        Tool.DitherTool -> {
            pencilToolOnPixelTapped(coordinateTapped)
        }

        Tool.ShadingTool -> {
            binding.activityCanvasDrawingView.shadingMode = true
            pencilToolOnPixelTapped(coordinateTapped)
        }

        else -> {
            pencilToolOnPixelTapped(coordinateTapped)
        }
    }
}