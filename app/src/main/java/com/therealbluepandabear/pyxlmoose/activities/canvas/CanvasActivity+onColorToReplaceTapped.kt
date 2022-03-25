package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.graphics.Bitmap
import com.therealbluepandabear.pyxlmoose.extensions.replacePixelsByColor

fun extendedOnColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap {
    previewColorToReplace = colorToReplace

    val bmp = getCoverImageBitmap()
    bmp.replacePixelsByColor(previewColorToFind!!, previewColorToReplace!!)

    return bmp
}