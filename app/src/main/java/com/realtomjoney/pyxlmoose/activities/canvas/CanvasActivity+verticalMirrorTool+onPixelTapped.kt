package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition

fun CanvasActivity.verticalMirrorToolOnPixelTapped(coordinatesTapped: XYPosition) {
    if (canvasInstance.myCanvasViewInstance.currentBitmapAction != null) {
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(XYPosition((canvasInstance.myCanvasViewInstance.extraBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y), canvasInstance.myCanvasViewInstance.extraBitmap.getPixel((canvasInstance.myCanvasViewInstance.extraBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y)))
    } else {
        canvasInstance.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(XYPosition((canvasInstance.myCanvasViewInstance.extraBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y), canvasInstance.myCanvasViewInstance.extraBitmap.getPixel((canvasInstance.myCanvasViewInstance.extraBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y)))
    }

    if (canvasInstance.myCanvasViewInstance.prevX != null && canvasInstance.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(canvasInstance.myCanvasViewInstance.extraBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor())

        lineAlgorithmInstance.compute(XYPosition(canvasInstance.myCanvasViewInstance.prevX!!, canvasInstance.myCanvasViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(XYPosition((canvasInstance.myCanvasViewInstance.extraBitmap.width - canvasInstance.myCanvasViewInstance.prevX!!) - 1, canvasInstance.myCanvasViewInstance.prevY!!), XYPosition((canvasInstance.myCanvasViewInstance.extraBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y))
    }

    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    canvasInstance.myCanvasViewInstance.overrideSetPixel((canvasInstance.myCanvasViewInstance.extraBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y, getSelectedColor())

    canvasInstance.myCanvasViewInstance.prevX = coordinatesTapped.x
    canvasInstance.myCanvasViewInstance.prevY = coordinatesTapped.y
}