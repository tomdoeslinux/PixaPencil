package com.realtomjoney.pyxlmoose.extensions

import android.graphics.Bitmap

fun Bitmap.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    for (i_1 in 0 until this.width) {
        for (i_2 in 0 until this.height) {
            if (this.getPixel(i_1, i_2) == colorToFind) {
                this.setPixel(i_1, i_2, colorToReplace)
            }
        }
    }
}