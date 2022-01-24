package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Color

fun PixelGridView.extendedGetNumberOfUniqueColors(): List<Int> {
    val colors = mutableListOf<Int>()
    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            val colorAtPixel = pixelGridViewBitmap.getPixel(i_1, i_2)
            if (!colors.contains(pixelGridViewBitmap.getPixel(i_1, i_2)) && colorAtPixel != Color.TRANSPARENT) {
                colors.add(colorAtPixel)
            }
        }
    }

    return colors
}