package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.eraseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    primaryAlgorithmInfoParameter.color = Color.TRANSPARENT

    if (binding.activityCanvasPixelGridView.prevX != null && binding.activityCanvasPixelGridView.prevY != null) {
        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(binding.activityCanvasPixelGridView.prevX!!, binding.activityCanvasPixelGridView.prevY!!), coordinatesTapped)
    }

    canvasCommandsHelperInstance.overrideSetPixel(coordinatesTapped, Color.TRANSPARENT)

    binding.activityCanvasPixelGridView.prevX = coordinatesTapped.x
    binding.activityCanvasPixelGridView.prevY = coordinatesTapped.y
}