package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun eraseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    primaryAlgorithmInfoParameter.color = Color.TRANSPARENT

    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    pixelGridViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, Color.TRANSPARENT)

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y
}