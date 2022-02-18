package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.BitmapAction

fun PixelGridView.extendedApplyBitmapFilter(lambda: (Int) -> Int) {
    currentBitmapAction = BitmapAction(mutableListOf(), true)

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            if (pixelGridViewBitmap.getPixel(i_1, i_2) != Color.TRANSPARENT) {
                val color = lambda(pixelGridViewBitmap.getPixel(i_1, i_2))

                overrideSetPixel(i_1, i_2, color)
            }
        }
    }

    bitmapActionData.add(currentBitmapAction!!)

    invalidate()
}