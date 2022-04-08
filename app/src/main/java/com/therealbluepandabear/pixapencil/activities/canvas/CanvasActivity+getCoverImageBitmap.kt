package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.core.view.drawToBitmap

var gridWasEnabled = false

fun getCoverImageBitmap(): Bitmap {
    if (outerCanvasInstance.canvasFragment.pixelGridViewInstance.gridEnabled) {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.hideGrid()
        gridWasEnabled = true
    }

    val bmp = outerCanvasInstance.fragmentHost.drawToBitmap()
    val bmps: Bitmap?

    val matrix = Matrix()
    matrix.setRotate(outerCanvasInstance.getCurrentRotation())

    bmps = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, false)

    if (gridWasEnabled) {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.showGrid()
    }

    return bmps
}