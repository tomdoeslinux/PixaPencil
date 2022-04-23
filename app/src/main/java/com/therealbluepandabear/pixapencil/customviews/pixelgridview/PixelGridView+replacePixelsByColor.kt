package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.activities.canvas.saved
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates

fun PixelGridView.extendedReplacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    saved = false

    currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            val coordinates = Coordinates(i_1, i_2)

            if (pixelGridViewBitmap.getPixel(coordinates) == colorToFind) {
                overrideSetPixel(coordinates, colorToReplace, true, ignoreSymmetry = true)
            }
        }
    }

    bitmapActionData.add(currentBitmapAction!!)
    pixelGridViewInstance.currentBitmapAction = null

    invalidate()

}