package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Bitmap
import android.graphics.Matrix

fun PixelGridView.extendedCalculateMatrix(bm: Bitmap, newHeight: Int, newWidth: Int): Matrix {
    val width = bm.width
    val height = bm.height
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height

    this.scaleWidth = scaleWidth
    this.scaleHeight = scaleHeight

    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)

    return matrix
}