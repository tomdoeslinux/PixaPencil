package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition

fun eraseToolOnPixelTapped(coordinatesTapped: XYPosition) {
    if (canvasInstance.myCanvasViewInstance.currentBitmapAction != null) {
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
    } else {
        canvasInstance.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
    }

    if (canvasInstance.myCanvasViewInstance.prevX != null && canvasInstance.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, Color.TRANSPARENT)

        lineAlgorithmInstance.compute(XYPosition(canvasInstance.myCanvasViewInstance.prevX!!, canvasInstance.myCanvasViewInstance.prevY!!), coordinatesTapped)
    }

    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, Color.TRANSPARENT)

    canvasInstance.myCanvasViewInstance.prevX = coordinatesTapped.x
    canvasInstance.myCanvasViewInstance.prevY = coordinatesTapped.y
}