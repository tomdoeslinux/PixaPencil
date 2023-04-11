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

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.undo
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinate
import com.therealbluepandabear.pixapencil.utility.constants.Flags

fun CanvasActivity.polygonToolOnPixelTapped(coordinateTapped: Coordinate) {
    Flags.DisableActionMove = true

    val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

    polygonCoordinates.add(coordinateTapped)

    if (polygonCoordinates.size > 1) {
        canvasCommandsHelperInstance.undo()

        for (i in 0 until polygonCoordinates.size - 2) {
            lineAlgorithmInstance.compute(
                polygonCoordinates[cindx - (i + 1)],
                polygonCoordinates[cindx - i]
            )
        }

        lineAlgorithmInstance.compute(polygonCoordinates[cindx], polygonCoordinates[cindx + 1])
        lineAlgorithmInstance.compute(polygonCoordinates[0], polygonCoordinates[cindx + 1])

        cindx += 1
    }
}