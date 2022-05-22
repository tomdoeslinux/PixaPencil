package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.eraseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    primaryAlgorithmInfoParameter.color = Color.TRANSPARENT

    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    canvasCommandsHelperInstance.overrideSetPixel(coordinatesTapped, Color.TRANSPARENT)

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y
}