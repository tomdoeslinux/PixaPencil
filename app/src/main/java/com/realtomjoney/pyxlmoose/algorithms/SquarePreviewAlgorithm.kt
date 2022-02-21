package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.activities.canvas.coordinates
import com.realtomjoney.pyxlmoose.models.Coordinates

class SquarePreviewAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private var endCoordinates: Coordinates? = null) {
    private fun setsqc1(lineAlgorithmInstance: LineAlgorithm, from: Coordinates, size: Int) {
        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(from.x + size, from.y))
            compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + size))
            compute(Coordinates(from.x, from.y + size), Coordinates(from.x + size, from.y + size))
            compute(Coordinates(from.x + size, from.y + size), Coordinates(from.x + size, from.y))
        }

        endCoordinates = Coordinates(from.x + size, from.y + size)
    }

    private fun setsqc2(lineAlgorithmInstance: LineAlgorithm, from: Coordinates, size: Int) {
        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + size))
            compute(Coordinates(from.x, from.y), Coordinates(from.x + size, from.y))
            compute(Coordinates(from.x + size, from.y), Coordinates(from.x + size, from.y + size))
            compute(Coordinates(from.x + size, from.y + size), Coordinates(from.x, from.y + size))
        }

        endCoordinates = Coordinates(from.x + size, from.y + size)
    }

    private fun setsqc3(lineAlgorithmInstance: LineAlgorithm, from: Coordinates, size: Int) {
        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(from.x - size, from.y))
            compute(Coordinates(from.x - size, from.y), Coordinates(from.x - size, from.y + size))
            compute(Coordinates(from.x - size, from.y + size), Coordinates(from.x, from.y + size))
            compute(Coordinates(from.x, from.y + size), Coordinates(from.x, from.y))
        }

        endCoordinates = Coordinates(from.x - size, from.y + size)
    }

    private fun setsqc4(lineAlgorithmInstance: LineAlgorithm, from: Coordinates, to: Coordinates) {

        val size = (from.x - to.x)

        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + size))
            compute(Coordinates(from.x, from.y + size), Coordinates(from.x - size, from.y + size))
            compute(Coordinates(from.x - size, from.y + size), Coordinates(from.x - size, from.y))
            compute(Coordinates(from.x - size, from.y), Coordinates(from.x, from.y))
        }

        endCoordinates = Coordinates(from.x - size, from.y + size)
    }

    fun compute(from: Coordinates, to: Coordinates) {
        val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, true)

        val fromDouble = from.convertToCoordinatesDouble()
        val toDouble = to.convertToCoordinatesDouble()

        val gradient: Double = (fromDouble.y - toDouble.y) / (fromDouble.x - toDouble.x)

        val size = if (gradient > 1.0) {
            to.x - from.x
        } else {
            to.y - from.y
        }

        if (gradient in 0.0..1.0) {
            setsqc1(lineAlgorithmInstance, from, size)
        } else if (gradient > 1.0 && gradient > 0){
            setsqc2(lineAlgorithmInstance, from, size)
        } else if (gradient < 0 && gradient > -1.0) {
            setsqc3(lineAlgorithmInstance, from, size)
        } else {
            setsqc4(lineAlgorithmInstance, from, to)
        }

        coordinates = endCoordinates
    }
}