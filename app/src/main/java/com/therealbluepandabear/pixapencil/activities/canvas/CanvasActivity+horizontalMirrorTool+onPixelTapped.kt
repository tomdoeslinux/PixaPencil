package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun horizontalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, (pixelGridViewInstance.pixelGridViewBitmap.height - pixelGridViewInstance.prevY!!) - 1), Coordinates(coordinatesTapped.x, (pixelGridViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1))
    }

    pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, (pixelGridViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1, getSelectedColor())

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y
}
