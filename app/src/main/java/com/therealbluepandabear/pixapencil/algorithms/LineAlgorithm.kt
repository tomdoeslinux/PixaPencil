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
import com.therealbluepandabear.pixapencil.models.Coordinates
import kotlin.math.abs

class LineAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val ignoreBrush: Boolean = false) {
    private fun drawLineY(from: Coordinates, to: Coordinates) {
        var x = from.x
        var y = from.y

        val differenceX = to.x - x
        var differenceY = to.y - y

        var yi = 1
        val xi = 1

        if (differenceY < 0) {
            differenceY = -differenceY
            yi = -1
        }

        var p = 2 * differenceY - differenceX

        while (x <= to.x) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(x, y), algorithmInfo.color, ignoreBrush = ignoreBrush)
            x++

            if (p < 0) {
                p += 2 * differenceY
                if (differenceY > differenceX) {
                    x += xi
                }
            } else {
                p = p + 2 * differenceY - 2 * differenceX
                y += yi
            }
        }
    }

    private fun drawLineX(from: Coordinates, to: Coordinates) {
        var x = from.x
        var y = from.y

        var differenceX = to.x - x
        val differenceY = to.y - y

        var xi = 1

        if (differenceX <= 0) {
            differenceX = -differenceX
            xi = -1
        }

        var p = 2 * differenceX - differenceY

        while (y <= to.y) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(x, y), algorithmInfo.color, ignoreBrush)
            y++

            if (p < 0) {
                p += 2 * differenceX
            } else {
                p = p + 2 * differenceX - 2 * differenceY
                x += xi
            }
        }
    }

    fun compute(p1: Coordinates, p2: Coordinates) {
        val x = p1.x
        val y = p1.y

        val differenceX = p2.x - x
        val differenceY = p2.y - y

        if (differenceY <= differenceX) {
            if (abs(differenceY) > differenceX) {
                drawLineX(p2, p1)
            } else {
                drawLineY(p1, p2)
            }
        } else {
            if (abs(differenceX) > differenceY) {
                drawLineY(p2, p1)
            } else {
                drawLineX(p1, p2)
            }
        }
    }
}