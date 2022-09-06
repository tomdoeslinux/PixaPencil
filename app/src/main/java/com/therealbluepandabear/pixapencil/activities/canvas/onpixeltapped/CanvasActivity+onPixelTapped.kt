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
import com.therealbluepandabear.pixapencil.models.Coordinates

var polygonCoordinates = mutableListOf<Coordinates>()
var cindx = 0

fun CanvasActivity.extendedOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!primaryAlgorithmInfoParameterInitialized) {
        initPrimaryAlgorithmInfoParameter()
    }

    viewModel.saved = false
    when (viewModel.currentTool) {
        Tool.PencilTool -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        Tool.EraseTool -> {
            eraseToolOnPixelTapped(coordinatesTapped)
        }

        Tool.ColorPickerTool -> {
            colorPickerToolOnPixelTapped(coordinatesTapped)
        }

        Tool.FillTool -> {
            fillToolOnPixelTapped(coordinatesTapped)
        }

        Tool.LineTool -> {
            lineToolOnPixelTapped(coordinatesTapped)
        }

        Tool.RectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedRectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.SquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedSquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.EllipseTool -> {
            ellipseToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedEllipseTool -> {
            ellipseToolOnPixelTapped(coordinatesTapped)
        }

        Tool.CircleTool -> {
            circleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedCircleTool -> {
            circleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.SprayTool -> {
            sprayToolOnPixelTapped(coordinatesTapped)
        }

        Tool.PolygonTool -> {
            polygonToolOnPixelTapped(coordinatesTapped)
        }

        Tool.DitherTool -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        Tool.ShadingTool -> {
            binding.activityCanvasPixelGridView.shadingMode = true
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        else -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }
    }
}