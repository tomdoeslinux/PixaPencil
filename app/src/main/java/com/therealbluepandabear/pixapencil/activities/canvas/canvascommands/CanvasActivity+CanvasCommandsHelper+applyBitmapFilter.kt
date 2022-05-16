package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.saved
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.applyBitmapFilter(filterLambda: (Int) -> Int) {
    saved = false
    viewModelReference.currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewInstance.pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewInstance.pixelGridViewBitmap.height) {
            val coordinates = Coordinates(i_1, i_2)
            val colorAtCoordinates = pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinates)

            if (colorAtCoordinates != Color.TRANSPARENT) {
                val color = filterLambda(colorAtCoordinates)

                overrideSetPixel(coordinates, color, true, ignoreSymmetry = true)
            }
        }
    }

    viewModelReference.bitmapActionData.add(viewModelReference.currentBitmapAction!!)
    viewModelReference.currentBitmapAction = null

    pixelGridViewInstance.invalidate()
}