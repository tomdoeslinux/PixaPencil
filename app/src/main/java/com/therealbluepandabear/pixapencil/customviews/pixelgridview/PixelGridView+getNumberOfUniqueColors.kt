package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

fun PixelGridView.extendedGetNumberOfUniqueColors(): List<Int> {
    val colors = mutableListOf<Int>()
    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            val coordinates = Coordinates(i_1, i_2)
            val colorAtPixel = pixelGridViewBitmap.getPixel(coordinates)

            if (!colors.contains(colorAtPixel) && colorAtPixel != Color.TRANSPARENT) {
                colors.add(colorAtPixel)
            }
        }
    }

    return colors
}