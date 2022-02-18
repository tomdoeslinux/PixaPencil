package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.models.BitmapAction

fun PixelGridView.extendedReplacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            if (pixelGridViewBitmap.getPixel(i_1, i_2) == colorToFind) {
                overrideSetPixel(i_1, i_2, colorToReplace)
            }
        }
    }

    bitmapActionData.add(currentBitmapAction!!)

    invalidate()
}