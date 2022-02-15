package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun CanvasActivity.verticalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if ( outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction != null) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y), outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y)))
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y),  outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y)))
    }

    if ( outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX != null &&  outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(AlgorithmInfoParameter( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor()))

        lineAlgorithmInstance.compute(Coordinates( outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX!!,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width -  outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX!!) - 1,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY!!), Coordinates(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y))
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(( outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y, getSelectedColor())

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY = coordinatesTapped.y
}