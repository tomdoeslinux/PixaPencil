package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition

fun CanvasActivity.horizontalMirrorToolOnPixelTapped(coordinatesTapped: XYPosition) {
    if (canvasInstance.myCanvasViewInstance.currentBitmapAction != null) {
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(XYPosition(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.extraBitmap.height - coordinatesTapped.y) - 1), canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x,(canvasInstance.myCanvasViewInstance.extraBitmap.height - coordinatesTapped.y) - 1)))
    } else {
        canvasInstance.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(XYPosition(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.extraBitmap.height - coordinatesTapped.y) - 1), canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x,(canvasInstance.myCanvasViewInstance.extraBitmap.height - coordinatesTapped.y) - 1)))
    }

    if (canvasInstance.myCanvasViewInstance.prevX != null && canvasInstance.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(canvasInstance.myCanvasViewInstance.extraBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor())

        lineAlgorithmInstance.compute(XYPosition(canvasInstance.myCanvasViewInstance.prevX!!, canvasInstance.myCanvasViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(XYPosition(canvasInstance.myCanvasViewInstance.prevX!!, (canvasInstance.myCanvasViewInstance.extraBitmap.height - canvasInstance.myCanvasViewInstance.prevY!!) - 1), XYPosition(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.extraBitmap.height - coordinatesTapped.y) - 1))
    }

    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.extraBitmap.height - coordinatesTapped.y) - 1, getSelectedColor())

    canvasInstance.myCanvasViewInstance.prevX = coordinatesTapped.x
    canvasInstance.myCanvasViewInstance.prevY = coordinatesTapped.y
}
