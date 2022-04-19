package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.activities.canvas.saved
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun PixelGridView.extendedReplacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    saved = false

    currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            if (pixelGridViewBitmap.getPixel(i_1, i_2) == colorToFind) {
                overrideSetPixel(i_1, i_2, colorToReplace, true)
            }
        }
    }

    bitmapActionData.add(currentBitmapAction!!)
    pixelGridViewInstance.currentBitmapAction = null

    invalidate()

}