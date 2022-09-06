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

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.models.Coordinates

class ExpandToNeighborsAlgorithm(private val bitmap: Bitmap) {
    fun compute(seed: Coordinates): List<Coordinates> {
        val toReturn = mutableListOf<Coordinates>()

        val ex1 = Coordinates(seed.x - 1, seed.y)
        val ex2 = Coordinates(seed.x + 1, seed.y)
        val ex3 = Coordinates(seed.x, seed.y - 1)
        val ex4 = Coordinates(seed.x, seed.y + 1)

        if (seed.x > 0) {
            toReturn.add(ex1)
        }

        if (seed.x < bitmap.width - 1) {
            toReturn.add(ex2)
        }

        if (seed.y > 0) {
            toReturn.add(ex3)
        }

        if (seed.y < bitmap.height - 1) {
            toReturn.add(ex4)
        }

        return toReturn
    }
}