package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.graphics.Color

fun PixelGridView.extendedClearCanvas() {
    for (i_1 in 0 until extraBitmap.width) {
        for (i_2 in 0 until extraBitmap.height) {
            extraBitmap.setPixel(i_1, i_2, Color.TRANSPARENT)
        }
    }
    invalidate()
    bitmapActionData.clear()
}