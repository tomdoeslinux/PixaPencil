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

class SquarePreviewAlgorithm(algorithmInfo: AlgorithmInfoParameter, private val invisibleMode: Boolean = false) {
    private val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, ignoreBrush = false)

    private var endCoordinate: Coordinate? = null

    private fun setsqc1(from: Coordinate, size: Int) {
        if (!invisibleMode) {
            lineAlgorithmInstance.compute(Coordinate(from.x, from.y), Coordinate(from.x + size, from.y))
            lineAlgorithmInstance.compute(Coordinate(from.x, from.y), Coordinate(from.x, from.y + size))
            lineAlgorithmInstance.compute(
                Coordinate(from.x, from.y + size),
                Coordinate(from.x + size, from.y + size)
            )
            lineAlgorithmInstance.compute(
                Coordinate(from.x + size, from.y + size),
                Coordinate(from.x + size, from.y)
            )
        }

        endCoordinate = Coordinate(from.x + size, from.y + size)
    }

    private fun setsqc2(from: Coordinate, size: Int) {
        if (!invisibleMode) {
            lineAlgorithmInstance.apply {
                compute(Coordinate(from.x, from.y), Coordinate(from.x, from.y + size))
                compute(Coordinate(from.x, from.y), Coordinate(from.x + size, from.y))
                compute(
                    Coordinate(from.x + size, from.y),
                    Coordinate(from.x + size, from.y + size)
                )
                compute(
                    Coordinate(from.x + size, from.y + size),
                    Coordinate(from.x, from.y + size)
                )
            }
        }

        endCoordinate = Coordinate(from.x + size, from.y + size)
    }

    private fun setsqc3(from: Coordinate, size: Int) {
        if (!invisibleMode) {
            lineAlgorithmInstance.apply {
                compute(Coordinate(from.x, from.y), Coordinate(from.x - size, from.y))
                compute(
                    Coordinate(from.x - size, from.y),
                    Coordinate(from.x - size, from.y + size)
                )
                compute(
                    Coordinate(from.x - size, from.y + size),
                    Coordinate(from.x, from.y + size)
                )
                compute(Coordinate(from.x, from.y + size), Coordinate(from.x, from.y))
            }
        }

        endCoordinate = Coordinate(from.x - size, from.y + size)
    }

    private fun setsqc4(from: Coordinate, to: Coordinate) {
        val size = (from.x - to.x)

        if (!invisibleMode) {
            lineAlgorithmInstance.apply {
                compute(Coordinate(from.x, from.y), Coordinate(from.x, from.y + size))
                compute(
                    Coordinate(from.x, from.y + size),
                    Coordinate(from.x - size, from.y + size)
                )
                compute(
                    Coordinate(from.x - size, from.y + size),
                    Coordinate(from.x - size, from.y)
                )
                compute(Coordinate(from.x - size, from.y), Coordinate(from.x, from.y))
            }
        }

        endCoordinate = Coordinate(from.x - size, from.y + size)
    }

    fun compute(from: Coordinate, to: Coordinate): Coordinate? {
        val fromDouble = from.convertToCoordinatesDouble()
        val toDouble = to.convertToCoordinatesDouble()

        val gradient: Double = (fromDouble.y - toDouble.y) / (fromDouble.x - toDouble.x)

        val size = if (gradient > 1.0) {
            to.x - from.x
        } else {
            to.y - from.y
        }

        if (gradient in 0.0..1.0) {
            setsqc1(from, size)
        } else if (gradient > 1.0){
            setsqc2(from, size)
        } else if (gradient < 0 && gradient > -1.0) {
            setsqc3(from, size)
        } else {
            setsqc4(from, to)
        }

        return endCoordinate
    }
}