package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.core.view.drawToBitmap

fun getCoverImageBitmap(): Bitmap {
    val bmp = outerCanvasInstance.fragmentHost.drawToBitmap()
    val bmps: Bitmap?

    val matrix = Matrix()
    matrix.setRotate(outerCanvasInstance.getCurrentRotation())

    bmps = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.width, matrix, false)

    return bmps
}