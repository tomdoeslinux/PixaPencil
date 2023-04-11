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

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinate

fun CanvasActivity.eraseToolOnPixelTapped(coordinateTapped: Coordinate) {
    primaryAlgorithmInfoParameter.color = Color.TRANSPARENT

    if (binding.activityCanvasDrawingView.prevX != null && binding.activityCanvasDrawingView.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinate(binding.activityCanvasDrawingView.prevX!!, binding.activityCanvasDrawingView.prevY!!), coordinateTapped)
    }

    canvasCommandsHelperInstance.overrideSetPixel(coordinateTapped, Color.TRANSPARENT)

    binding.activityCanvasDrawingView.prevX = coordinateTapped.x
    binding.activityCanvasDrawingView.prevY = coordinateTapped.y
}