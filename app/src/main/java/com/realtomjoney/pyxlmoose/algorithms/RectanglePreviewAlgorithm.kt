package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.models.Coordinates

class RectanglePreviewAlgorithm(private val algorithmInfo: AlgorithmInfoParameter) {
    fun compute(from: Coordinates, to: Coordinates) {
        val modifiedShapeAlgorithmInfo = algorithmInfo
        modifiedShapeAlgorithmInfo.color = algorithmInfo.color

        val lineAlgorithmInstance = LineAlgorithm(modifiedShapeAlgorithmInfo, true)

        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(to.x, from.y))
            compute(Coordinates(to.x, to.y), Coordinates(to.x, from.y))
            compute(Coordinates(from.x, to.y), Coordinates(from.x, from.y))
            compute(Coordinates(to.x, to.y), Coordinates(from.x, to.y))
        }
    }
}