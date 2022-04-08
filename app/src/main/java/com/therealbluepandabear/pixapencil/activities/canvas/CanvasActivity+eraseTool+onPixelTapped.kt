package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates

fun eraseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    primaryAlgorithmInfoParameter.color = Color.TRANSPARENT

    if (outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX != null && outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX!!, outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, Color.TRANSPARENT)

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.prevY = coordinatesTapped.y
}