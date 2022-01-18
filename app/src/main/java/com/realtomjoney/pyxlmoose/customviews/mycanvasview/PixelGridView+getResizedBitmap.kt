package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.graphics.Bitmap
import android.graphics.Matrix

fun PixelGridView.getResizedBitmap(bm: Bitmap, newHeight: Int, newWidth: Int): Bitmap? {
    val width = bm.width
    val height = bm.height
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height

    this.scaleWidth = scaleWidth
    this.scaleHeight = scaleHeight

    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)

    return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
}