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

package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.CoordinatesDouble

class CircleAlgorithm(algorithmInfo: AlgorithmInfoParameter, filledMode: Boolean = false) {
    private val filledMp = MidpointCircleAlgorithm(algorithmInfo, filledMode = filledMode)
    private val filledMpEvenDiameterMode = MidpointCircleAlgorithm(algorithmInfo, true, filledMode = filledMode)

    fun compute(p1: Coordinates, p2: Coordinates) {
        val p1Double = p1.convertToCoordinatesDouble()
        val p2Double = p2.convertToCoordinatesDouble()

        val x1d = p1Double.x
        val y1d = p1Double.y

        var x2d = p2Double.x
        var y2d = p2Double.y

        var midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

        val mx = midpoint.x
        val my = midpoint.y

        if (mx % 1.0 == 0.0 || my % 1.0 == 0.0) {
            val radius = (p2.x - mx.toInt())
            filledMp.compute(Coordinates(mx.toInt(), my.toInt()), radius)
        } else {
            x2d--
            y2d--

            midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

            val mxN = midpoint.x
            val radius = (p2.x - mxN.toInt()) - 1

            filledMpEvenDiameterMode.compute(Coordinates(mx.toInt(), my.toInt()), radius)
        }
    }
}