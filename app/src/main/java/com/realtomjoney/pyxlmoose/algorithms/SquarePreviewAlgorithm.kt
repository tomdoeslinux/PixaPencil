package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.activities.canvas.coordinates
import com.realtomjoney.pyxlmoose.models.Coordinates

class SquarePreviewAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private var endCoords: Coordinates? = null) {
    fun compute(from: Coordinates, to: Coordinates) {
        val modifiedShapeAlgorithmInfo = algorithmInfo
        modifiedShapeAlgorithmInfo.color = algorithmInfo.color

        val lineAlgorithmInstance = LineAlgorithm(modifiedShapeAlgorithmInfo, true)

        val gradient: Double = (from.y.toDouble() - to.y.toDouble()) / (from.x.toDouble() - to.x.toDouble())

        var dimen = if (gradient > 1.0) to.x - from.x else to.y - from.y

        if (gradient in 0.0..1.0) {
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x + dimen, from.y))
                compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + dimen))
                compute(
                    Coordinates(from.x, from.y + dimen),
                    Coordinates(from.x + dimen, from.y + dimen)
                )
                compute(
                    Coordinates(from.x + dimen, from.y + dimen),
                    Coordinates(from.x + dimen, from.y)
                )
            }

            endCoords = Coordinates(from.x + dimen, from.y + dimen)
        } else if (gradient > 1.0 && gradient > 0){
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + dimen))
                compute(Coordinates(from.x, from.y), Coordinates(from.x + dimen, from.y))
                compute(
                    Coordinates(from.x + dimen, from.y),
                    Coordinates(from.x + dimen, from.y + dimen)
                )
                compute(
                    Coordinates(from.x + dimen, from.y + dimen),
                    Coordinates(from.x, from.y + dimen)
                )
            }

            endCoords = Coordinates(from.x + dimen, from.y + dimen)
        } else if (gradient < 0 && gradient > -1.0) {
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x - dimen, from.y))
                compute(Coordinates(from.x - dimen, from.y), Coordinates(from.x - dimen, from.y + dimen))
                compute(Coordinates(from.x - dimen, from.y + dimen), Coordinates(from.x, from.y + dimen))
                compute(Coordinates(from.x, from.y + dimen), Coordinates(from.x, from.y))
            }

            endCoords = Coordinates(from.x - dimen, from.y + dimen)
        } else {
            dimen = (from.x - to.x)
            lineAlgorithmInstance.apply {
                compute(Coordinates(from.x, from.y), Coordinates(from.x, from.y + dimen))
                compute(Coordinates(from.x, from.y + dimen), Coordinates(from.x - dimen, from.y + dimen))
                compute(Coordinates(from.x - dimen, from.y + dimen), Coordinates(from.x - dimen, from.y))
                compute(Coordinates(from.x - dimen, from.y), Coordinates(from.x, from.y))
            }

            endCoords = Coordinates(from.x - dimen, from.y + dimen)
        }
        coordinates = endCoords
    }
}