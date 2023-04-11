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

import com.therealbluepandabear.pixapencil.models.Coordinate
import com.therealbluepandabear.pixapencil.models.CoordinatesDouble
import kotlin.math.abs

class EllipseAlgorithm(algorithmInfo: AlgorithmInfoParameter, filledMode: Boolean = false) {
    private val mp1 = MidpointEllipseAlgorithm(algorithmInfo, filledMode = filledMode)
    private val mp2 = MidpointEllipseAlgorithm(algorithmInfo, xDEC = true, yDEC = false, filledMode = filledMode)
    private val mp3 = MidpointEllipseAlgorithm(algorithmInfo, xDEC = false, yDEC = true, filledMode = filledMode)
    private val mp4 = MidpointEllipseAlgorithm(algorithmInfo, xDEC = true, yDEC = true, filledMode = filledMode)

    fun compute(p1: Coordinate, p2: Coordinate) {
        val p1Double = p1.convertToCoordinatesDouble()
        val p2Double = p2.convertToCoordinatesDouble()

        val x1d = p1Double.x
        val y1d = p1Double.y

        val x2d = p2Double.x
        val y2d = p2Double.y

        val midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

        val mx = midpoint.x
        val my = midpoint.y

        val rY = abs((p2.y - p1.y) / 2)
        val rX = abs((p2.x - p1.x) / 2)

        if (mx % 1.0 == 0.0 && my % 1.0 == 0.0) {
            mp1.compute(Coordinate(mx.toInt(), my.toInt()), rX, rY)
        } else if (mx % 1.0 != 0.0 && my % 1.0 == 0.0) {
            mp2.compute(Coordinate(mx.toInt(), my.toInt()), rX, rY)
        } else if (mx % 1.0 == 0.0 && my % 1.0 != 0.0) {
            mp3.compute(Coordinate(mx.toInt(), my.toInt()), rX, rY)
        } else {
            mp4.compute(Coordinate(mx.toInt(), my.toInt()), rX, rY)
        }
    }
}