package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun verticalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.pixelGridViewBitmap.width - pixelGridViewInstance.prevX!! - 1, pixelGridViewInstance.prevY!!), Coordinates((pixelGridViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y))
    }

    pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    pixelGridViewInstance.overrideSetPixel((pixelGridViewInstance.pixelGridViewBitmap.width - coordinatesTapped.x) - 1, coordinatesTapped.y, getSelectedColor())

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y
}