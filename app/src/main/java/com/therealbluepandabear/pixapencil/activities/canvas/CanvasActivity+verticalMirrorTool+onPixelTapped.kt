package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates

fun verticalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX != null &&  outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates( outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX!!,  outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(( outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.width -  outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX!!) - 1,  outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY!!), Coordinates(( outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y))
    }

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.overrideSetPixel(( outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y, getSelectedColor())

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY = coordinatesTapped.y
}