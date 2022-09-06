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

class RectanglePreviewAlgorithm(algorithmInfo: AlgorithmInfoParameter, private val invisibleMode: Boolean = false)  {
    private val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, ignoreBrush = false)

    fun compute(from: Coordinates, to: Coordinates): Coordinates {
        if (!invisibleMode) {
            lineAlgorithmInstance.compute(Coordinates(from.x, from.y), Coordinates(to.x, from.y))
            lineAlgorithmInstance.compute(Coordinates(to.x, to.y), Coordinates(to.x, from.y))
            lineAlgorithmInstance.compute(Coordinates(from.x, to.y), Coordinates(from.x, from.y))
            lineAlgorithmInstance.compute(Coordinates(to.x, to.y), Coordinates(from.x, to.y))
        }

        return Coordinates(to.x, to.y)
    }
}