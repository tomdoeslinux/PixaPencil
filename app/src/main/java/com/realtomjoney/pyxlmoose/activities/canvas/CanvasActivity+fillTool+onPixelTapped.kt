package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.FloodFillAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates

fun fillToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val floodFillAlgorithmInstance = FloodFillAlgorithm(
        AlgorithmInfoParameter.pass(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
        getSelectedColor())
    )

    floodFillAlgorithmInstance.compute(Coordinates(coordinatesTapped.x, coordinatesTapped.y))
}