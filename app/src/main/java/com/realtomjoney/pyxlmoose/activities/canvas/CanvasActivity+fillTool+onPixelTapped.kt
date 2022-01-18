package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.FloodFillAlgorithm
import com.realtomjoney.pyxlmoose.models.XYPosition

fun CanvasActivity.fillToolOnPixelTapped(coordinatesTapped: XYPosition) {
    val floodFillAlgorithmInstance = FloodFillAlgorithm(canvasInstance.myCanvasViewInstance.extraBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor())
    floodFillAlgorithmInstance.compute(XYPosition(coordinatesTapped.x, coordinatesTapped.y))
}