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

package com.therealbluepandabear.pixapencil.models

import kotlin.math.floor

data class Coordinates(val x: Int, val y: Int) {
    private fun swap(): Coordinates {
        return Coordinates(y, x)
    }

    fun convertToCoordinatesDouble(): CoordinatesDouble {
        return CoordinatesDouble(x.toDouble(), y.toDouble())
    }

    fun getHorizontallyReflectedCoordinates(bitmapHeight: Int): Coordinates {
        return Coordinates(x, (bitmapHeight - y) - 1)
    }

    fun getVerticallyReflectedCoordinates(bitmapWidth: Int): Coordinates {
        return Coordinates((bitmapWidth - x) - 1, y)
    }

    fun getQuadReflectedCoordinateSet(bitmapWidth: Int, bitmapHeight: Int): List<Coordinates> {
        val horizontallyReflectedCoordinates = getHorizontallyReflectedCoordinates(bitmapHeight)
        val verticallyReflectedCoordinates = getVerticallyReflectedCoordinates(bitmapWidth)

        return listOf(
            horizontallyReflectedCoordinates,
            verticallyReflectedCoordinates,
            Coordinates(verticallyReflectedCoordinates.x, horizontallyReflectedCoordinates.y)
        )
    }

    fun getOctalReflectedCoordinateSet(bitmapWidth: Int, bitmapHeight: Int): List<Coordinates> {
        val octalReflectedCoordinateSet = mutableListOf<Coordinates>()

        octalReflectedCoordinateSet.addAll(getQuadReflectedCoordinateSet(bitmapWidth, bitmapHeight))

        val coordinates1 = Coordinates(y, getVerticallyReflectedCoordinates(bitmapWidth).x)
        val coordinates2 = Coordinates(getHorizontallyReflectedCoordinates(bitmapHeight).y, x)

        octalReflectedCoordinateSet.add(coordinates1.swap())
        octalReflectedCoordinateSet.add(coordinates2.swap())

        val coordinates3 = getVerticallyReflectedCoordinates(bitmapWidth)
        val coordinates4 = coordinates2.getVerticallyReflectedCoordinates(bitmapWidth)

        octalReflectedCoordinateSet.add(coordinates3)
        octalReflectedCoordinateSet.add(coordinates4)

        val coordinates5 = coordinates3.getHorizontallyReflectedCoordinates(bitmapHeight)
        val coordinates6 = coordinates4.getHorizontallyReflectedCoordinates(bitmapHeight)

        octalReflectedCoordinateSet.add(coordinates5)
        octalReflectedCoordinateSet.add(coordinates6)

        octalReflectedCoordinateSet.add(coordinates5.getHorizontallyReflectedCoordinates(bitmapHeight))
        octalReflectedCoordinateSet.add(coordinates6.getHorizontallyReflectedCoordinates(bitmapHeight))

        octalReflectedCoordinateSet.add(coordinates5.getHorizontallyReflectedCoordinates(bitmapHeight).getVerticallyReflectedCoordinates(bitmapWidth))
        octalReflectedCoordinateSet.add(coordinates6.getHorizontallyReflectedCoordinates(bitmapHeight).getVerticallyReflectedCoordinates(bitmapWidth))
        octalReflectedCoordinateSet.add(coordinates4.getHorizontallyReflectedCoordinates(bitmapHeight).getVerticallyReflectedCoordinates(bitmapWidth))

        return octalReflectedCoordinateSet
    }

    companion object {
        fun staticSet(x: Int, y: Int): Coordinates {
            return Coordinates(x, y)
        }

        fun fromIndex(index: Int, bitmapWidth: Int): Coordinates {
            return Coordinates(
                x = (index.toDouble() % bitmapWidth.toDouble()).toInt(),
                y = floor(index.toDouble() / bitmapWidth).toInt()
            )
        }
    }
}
