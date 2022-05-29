package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.pencilToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        val color = if (pixelGridViewInstance.pixelPerfectMode) {
            pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped)
        } else {
            getSelectedColor()
        }
        primaryAlgorithmInfoParameter.color = color

        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    canvasCommandsHelperInstance.overrideSetPixel(coordinatesTapped, pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped))

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y


    if (pixelGridViewInstance.pixelPerfectMode) {
    val distinct = viewModel.currentBitmapAction!!.actionData.distinctBy { it.coordinates }
    val data = mutableListOf<BitmapActionData>()

    var index = 0

    while (index < distinct.size) {
        if (index > 0 && index + 1 < distinct.size
            && (distinct[index - 1].coordinates.x == distinct[index].coordinates.x || distinct[index - 1].coordinates.y == distinct[index].coordinates.y)
            && (distinct[index + 1].coordinates.x == distinct[index].coordinates.x || distinct[index + 1].coordinates.y == distinct[index].coordinates.y)
            && distinct[index - 1].coordinates.x != distinct[index + 1].coordinates.x
            && distinct[index - 1].coordinates.y != distinct[index + 1].coordinates.y
        ) {
            index += 1
        }

        data.add(distinct[index])

        index += 1
    }

    for (i in data) {
        canvasCommandsHelperInstance.overrideSetPixel(i.coordinates, getSelectedColor())
    }
    }
}
