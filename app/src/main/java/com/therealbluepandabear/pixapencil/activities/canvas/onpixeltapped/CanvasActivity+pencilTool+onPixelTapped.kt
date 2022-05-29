package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.PixelPerfectAlgorithm
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.pencilToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = if (pixelGridViewInstance.pixelPerfectMode && (pixelGridViewInstance.currentBrush == BrushesDatabase.toList().first() || pixelGridViewInstance.currentBrush == null)) {
        pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped)
    } else {
        getSelectedColor()
    }

    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        primaryAlgorithmInfoParameter.color = color

        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    canvasCommandsHelperInstance.overrideSetPixel(coordinatesTapped, color)

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y


    if (pixelGridViewInstance.pixelPerfectMode && (pixelGridViewInstance.currentBrush == BrushesDatabase.toList().first() || pixelGridViewInstance.currentBrush == null)) {
        for (i in PixelPerfectAlgorithm(primaryAlgorithmInfoParameter).compute()) {
            canvasCommandsHelperInstance.overrideSetPixel(i, getSelectedColor())
        }
    }
}
