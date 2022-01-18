package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.FloodFillAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates

fun CanvasActivity.fillToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val floodFillAlgorithmInstance = FloodFillAlgorithm(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor())
    floodFillAlgorithmInstance.compute(Coordinates(coordinatesTapped.x, coordinatesTapped.y))
}