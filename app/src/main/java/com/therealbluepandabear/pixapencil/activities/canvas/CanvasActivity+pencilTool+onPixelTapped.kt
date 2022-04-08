package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates

fun pencilToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX != null && outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX!!,  outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY = coordinatesTapped.y
}