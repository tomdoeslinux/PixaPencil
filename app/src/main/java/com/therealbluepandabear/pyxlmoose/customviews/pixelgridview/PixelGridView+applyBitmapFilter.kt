package com.therealbluepandabear.pyxlmoose.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pyxlmoose.models.BitmapAction

fun PixelGridView.extendedApplyBitmapFilter(filterLambda: (Int) -> Int) {
    currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            if (pixelGridViewBitmap.getPixel(i_1, i_2) != Color.TRANSPARENT) {
                val color = filterLambda(pixelGridViewBitmap.getPixel(i_1, i_2))

                overrideSetPixel(i_1, i_2, color, true)
            }
        }
    }

    bitmapActionData.add(currentBitmapAction!!)
    currentBitmapAction = null

    invalidate()
}