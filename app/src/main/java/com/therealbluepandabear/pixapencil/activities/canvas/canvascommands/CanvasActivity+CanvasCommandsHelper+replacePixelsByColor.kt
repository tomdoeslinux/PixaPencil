package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.saved
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    saved = false

    viewModelReference.currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewInstance.pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewInstance.pixelGridViewBitmap.height) {
            val coordinates = Coordinates(i_1, i_2)

            if (pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinates) == colorToFind) {
                overrideSetPixel(coordinates, colorToReplace, true, ignoreSymmetry = true)
            }
        }
    }

    viewModelReference.bitmapActionData.add(viewModelReference.currentBitmapAction!!)
    viewModelReference.currentBitmapAction = null

    pixelGridViewInstance.invalidate()
}