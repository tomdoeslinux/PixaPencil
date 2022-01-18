package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.graphics.Color

fun PixelGridView.extendedGetNumberOfUniqueColors(): List<Int> {
    val colors = mutableListOf<Int>()
    for (i_1 in 0 until extraBitmap.width) {
        for (i_2 in 0 until extraBitmap.height) {
            val colorAtPixel = extraBitmap.getPixel(i_1, i_2)
            if (!colors.contains(extraBitmap.getPixel(i_1, i_2)) && colorAtPixel != Color.TRANSPARENT) {
                colors.add(colorAtPixel)
            }
        }
    }

    return colors
}