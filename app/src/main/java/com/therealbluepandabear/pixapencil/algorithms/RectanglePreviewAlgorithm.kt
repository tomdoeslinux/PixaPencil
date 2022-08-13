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