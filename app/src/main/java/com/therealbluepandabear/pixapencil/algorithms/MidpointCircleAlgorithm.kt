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

/**
 * The code is based off of the following:
 *
 * - [GitHub Gist](https://gist.github.com/sahasayan/01653be282567b174c7dabb958b75104)
 * - [YouTube Video](https://www.youtube.com/watch?v=yfLm9nXsCvw)
 */

class MidpointCircleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val evenDiameterMode: Boolean = false, private val filledMode: Boolean = false) {
    private val shouldLineIgnoreBrush = true

    private val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

    private fun putPixel(p1: Coordinate, p2: Coordinate) {
        val xc = p1.x
        val yc = p1.y

        val x = p2.x
        val y = p2.y

        if (!evenDiameterMode) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc + x, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc + y, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc + x, yc + y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc + y, yc + x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - x, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - y, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - y, yc + x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - x, yc + y),
                algorithmInfo.color
            )

            if (filledMode) {
                lineAlgorithmInstance.compute(
                    Coordinate(xc + x, yc - y),
                    Coordinate(xc - x, yc - y)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - y, yc + x),
                    Coordinate(xc + y, yc + x)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - x, yc + y),
                    Coordinate(xc + x, yc + y)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - x, yc + y),
                    Coordinate(xc + x, yc + y)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - y, yc - x),
                    Coordinate(xc + y, yc - x)
                )
            }
        } else {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate((xc + x) + 1, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate((xc + y) + 1, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate((xc + x) + 1, (yc + y) + 1),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate((xc + y) + 1, (yc + x) + 1),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - x, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - y, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - y, (yc + x) + 1),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinate(xc - x, (yc + y) + 1),
                algorithmInfo.color
            )

            if (filledMode) {
                lineAlgorithmInstance.compute(
                    Coordinate((xc + x) + 1, yc - y),
                    Coordinate(xc - x, yc - y)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - y, (yc + x) + 1),
                    Coordinate((xc + y) + 1, (yc + x) + 1)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - x, (yc + y) + 1),
                    Coordinate((xc + x) + 1, (yc + y) + 1)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - x, (yc + y) + 1),
                    Coordinate((xc + x) + 1, (yc + y) + 1)
                )
                lineAlgorithmInstance.compute(
                    Coordinate(xc - y, yc - x),
                    Coordinate((xc + y) + 1, yc - x)
                )
            }
        }
    }

    fun compute(p1: Coordinate, radius: Int) {
        val xc = p1.x
        val yc = p1.y

        var x = 0
        var y = radius

        var p = 1 - radius

        putPixel(Coordinate(xc, yc), Coordinate(x, y))

        while (x < y) {
            p = if (p < 0) {
                x++
                (p + 2 * x + 1)
            } else {
                x++
                y--
                (p + 2 * (x - y) + 1)
            }
            putPixel(Coordinate(xc, yc), Coordinate(x, y))
        }
    }
}