package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.shapes.LineAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun CanvasActivity.horizontalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (canvasInstance.myCanvasViewInstance.currentBitmapAction != null) {
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1), canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x,(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1)))
    } else {
        canvasInstance.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1), canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x,(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1)))
    }

    if (canvasInstance.myCanvasViewInstance.prevX != null && canvasInstance.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(AlgorithmInfoParameter(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap, canvasInstance.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor()))

        lineAlgorithmInstance.compute(Coordinates(canvasInstance.myCanvasViewInstance.prevX!!, canvasInstance.myCanvasViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(canvasInstance.myCanvasViewInstance.prevX!!, (canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - canvasInstance.myCanvasViewInstance.prevY!!) - 1), Coordinates(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1))
    }

    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    canvasInstance.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, (canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1, getSelectedColor())

    canvasInstance.myCanvasViewInstance.prevX = coordinatesTapped.x
    canvasInstance.myCanvasViewInstance.prevY = coordinatesTapped.y
}
