package com.therealbluepandabear.pyxlmoose.algorithms

import com.therealbluepandabear.pyxlmoose.models.Coordinates

class RectanglePreviewAlgorithm(private val algorithmInfo: AlgorithmInfoParameter) {
    private val shouldRectIgnoreBrush = false

    fun compute(from: Coordinates, to: Coordinates) {
        val modifiedShapeAlgorithmInfo = algorithmInfo
        modifiedShapeAlgorithmInfo.color = algorithmInfo.color

        val lineAlgorithmInstance = LineAlgorithm(modifiedShapeAlgorithmInfo, shouldRectIgnoreBrush)

        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(to.x, from.y))
            compute(Coordinates(to.x, to.y), Coordinates(to.x, from.y))
            compute(Coordinates(from.x, to.y), Coordinates(from.x, from.y))
            compute(Coordinates(to.x, to.y), Coordinates(from.x, to.y))
        }
    }
}