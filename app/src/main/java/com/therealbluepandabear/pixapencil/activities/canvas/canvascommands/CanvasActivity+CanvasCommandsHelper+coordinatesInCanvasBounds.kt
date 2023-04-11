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

package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinate

fun CanvasActivity.CanvasCommandsHelper.coordinatesInCanvasBounds(coordinate: Coordinate, currentTool: Tool, ignoreDither: Boolean = false): Boolean {
    return if (currentTool == Tool.DitherTool && !ignoreDither) {
        (coordinate.x in 0 until baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.width && coordinate.y in 0 until baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.height &&
                baseReference.viewModel.currentDitherBrush.algorithm.invoke(coordinate))
    } else {
        (coordinate.x in 0 until  baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.width && coordinate.y in 0 until  baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.height)
    }
}