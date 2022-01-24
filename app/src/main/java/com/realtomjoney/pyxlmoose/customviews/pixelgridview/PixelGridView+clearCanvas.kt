package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Color

fun PixelGridView.extendedClearCanvas() {
    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            pixelGridViewBitmap.setPixel(i_1, i_2, Color.TRANSPARENT)
        }
    }
    invalidate()
    bitmapActionData.clear()
}