package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates

fun horizontalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX != null && outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX!!, outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX!!, (outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.height - outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY!!) - 1), Coordinates(coordinatesTapped.x, (outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1))
    }

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, (outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1, getSelectedColor())

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY = coordinatesTapped.y
}
