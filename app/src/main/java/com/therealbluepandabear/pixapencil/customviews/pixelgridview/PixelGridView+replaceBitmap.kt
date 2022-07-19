package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Bitmap
import android.graphics.Canvas

fun PixelGridView.extendedReplaceBitmap(newBitmap: Bitmap) {
    pixelGridViewBitmap = Bitmap.createBitmap(newBitmap.width, newBitmap.height, Bitmap.Config.ARGB_8888)
    pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
    setBitmapWidth(newBitmap.width)
    setBitmapHeight(newBitmap.height)
    pixelGridViewCanvas.drawBitmap(newBitmap, 0f, 0f, null)

    invalidate()
}