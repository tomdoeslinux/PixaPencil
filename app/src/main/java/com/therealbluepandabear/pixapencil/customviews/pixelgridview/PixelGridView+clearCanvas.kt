package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

fun PixelGridView.extendedClearCanvas() {
    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            pixelGridViewBitmap.setPixel(Coordinates(i_1, i_2), Color.TRANSPARENT)
        }
    }
    invalidate()
    bitmapActionData.clear()
}