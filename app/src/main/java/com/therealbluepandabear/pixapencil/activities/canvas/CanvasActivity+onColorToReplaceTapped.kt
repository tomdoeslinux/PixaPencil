package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.extensions.replacePixelsByColor

fun extendedOnColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap {
    previewColorToReplace = colorToReplace

    val bmp = getCoverImageBitmap()
    bmp.replacePixelsByColor(previewColorToFind!!, previewColorToReplace!!)

    return bmp
}