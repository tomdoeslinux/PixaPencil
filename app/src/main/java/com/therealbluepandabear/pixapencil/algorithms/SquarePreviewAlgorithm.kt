package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.models.Coordinates

class SquarePreviewAlgorithm(algorithmInfo: AlgorithmInfoParameter, private val invisibleMode: Boolean = false) {
    private val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, ignoreBrush = false)

    private var endCoordinates: Coordinates? = null

    private fun setsqc1(from: Coordinates, size: Int) {
        if (!invisibleMode) {
            lineAlgorithmInstance.compute(Coordinates(from.x, from.y), Coordinates(from.x + size, from.y))
            lineAlgorithmInstance.compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + size))
            lineAlgorithmInstance.compute(
                Coordinates(from.x, from.y + size),
                Coordinates(from.x + size, from.y + size)
            )
            lineAlgorithmInstance.compute(
                Coordinates(from.x + size, from.y + size),
                Coordinates(from.x + size, from.y)
            )
        }

        endCoordinates = Coordinates(from.x + size, from.y + size)
    }

    private fun setsqc2(from: Coordinates, size: Int) {
        if (!invisibleMode) {
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + size))
                compute(Coordinates(from.x, from.y), Coordinates(from.x + size, from.y))
                compute(
                    Coordinates(from.x + size, from.y),
                    Coordinates(from.x + size, from.y + size)
                )
                compute(
                    Coordinates(from.x + size, from.y + size),
                    Coordinates(from.x, from.y + size)
                )
            }
        }

        endCoordinates = Coordinates(from.x + size, from.y + size)
    }

    private fun setsqc3(from: Coordinates, size: Int) {
        if (!invisibleMode) {
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x - size, from.y))
                compute(
                    Coordinates(from.x - size, from.y),
                    Coordinates(from.x - size, from.y + size)
                )
                compute(
                    Coordinates(from.x - size, from.y + size),
                    Coordinates(from.x, from.y + size)
                )
                compute(Coordinates(from.x, from.y + size), Coordinates(from.x, from.y))
            }
        }

        endCoordinates = Coordinates(from.x - size, from.y + size)
    }

    private fun setsqc4(from: Coordinates, to: Coordinates) {
        val size = (from.x - to.x)

        if (!invisibleMode) {
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + size))
                compute(
                    Coordinates(from.x, from.y + size),
                    Coordinates(from.x - size, from.y + size)
                )
                compute(
                    Coordinates(from.x - size, from.y + size),
                    Coordinates(from.x - size, from.y)
                )
                compute(Coordinates(from.x - size, from.y), Coordinates(from.x, from.y))
            }
        }

        endCoordinates = Coordinates(from.x - size, from.y + size)
    }

    fun compute(from: Coordinates, to: Coordinates): Coordinates? {
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

        return endCoordinates
    }
}