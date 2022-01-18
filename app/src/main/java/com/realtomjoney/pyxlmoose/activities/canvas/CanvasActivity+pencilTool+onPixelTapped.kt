package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition

fun CanvasActivity.pencilToolOnPixelTapped(coordinatesTapped: XYPosition) {
    if (canvasInstance.myCanvasViewInstance.currentBitmapAction != null) {
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y),))
    } else {
        canvasInstance.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y),))
    }

    if (canvasInstance.myCanvasViewInstance.prevX != null && canvasInstance.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor())

        lineAlgorithmInstance.compute(XYPosition(canvasInstance.myCanvasViewInstance.prevX!!, canvasInstance.myCanvasViewInstance.prevY!!), coordinatesTapped)
    }

    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())

    canvasInstance.myCanvasViewInstance.prevX = coordinatesTapped.x
    canvasInstance.myCanvasViewInstance.prevY = coordinatesTapped.y
}