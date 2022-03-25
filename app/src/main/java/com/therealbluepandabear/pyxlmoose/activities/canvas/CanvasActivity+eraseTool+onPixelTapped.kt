package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pyxlmoose.algorithms.LineAlgorithm
import com.therealbluepandabear.pyxlmoose.models.Coordinates

fun eraseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    primaryAlgorithmInfoParameter.color = Color.TRANSPARENT

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX != null && outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX!!, outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY!!), coordinatesTapped)
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, Color.TRANSPARENT)

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY = coordinatesTapped.y
}