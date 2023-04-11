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

import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.models.Coordinate

class RectangleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val borderColor: Int? = null) {
    private fun drawBorder(from: Coordinate, to: Coordinate) {
        val modifiedShapeAlgorithmInfo = algorithmInfo
        modifiedShapeAlgorithmInfo.color = borderColor!!

        val lineAlgorithmInstance = LineAlgorithm(modifiedShapeAlgorithmInfo, true)

        lineAlgorithmInstance.compute(Coordinate(from.x, from.y), Coordinate(to.x, from.y))
        lineAlgorithmInstance.compute(Coordinate(to.x, to.y), Coordinate(to.x, from.y))
        lineAlgorithmInstance.compute(Coordinate(from.x, to.y), Coordinate(from.x, from.y))
        lineAlgorithmInstance.compute(Coordinate(to.x, to.y), Coordinate(from.x, to.y))
    }

    fun compute(p1: Coordinate, p2: Coordinate) {
        var (cx, cy) = p1

        if (p1.x >= p2.x && p1.y <= p2.y) {
            while (cx >= p2.x) {
                for (i in cy..p2.y) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx--
            }
        } else if (p1.x <= p2.x && p1.y <= p2.y) {
            while (cx <= p2.x) {
                for (i in cy..p2.y) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx++
            }
        } else if (p1.x <= p2.x) {
            while (cx <= p2.x) {
                for (i in p2.y..cy) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx++
            }
        } else {
            while (cx >= p2.x) {
                for (i in p2.y..cy) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx--
            }
        }
        if (borderColor != null) {
            drawBorder(p1, p2)
        }
    }
}