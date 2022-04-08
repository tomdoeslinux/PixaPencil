package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.utility.BitmapUtilities

var gridWasEnabled = false

fun getCoverImageBitmap(): Bitmap {
    if (outerCanvasInstance.canvasFragment.pixelGridViewInstance.gridEnabled) {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.hideGrid()
        gridWasEnabled = true
    }

    val bmp = outerCanvasInstance.fragmentHost.drawToBitmap()
    val bmp2 = outerCanvasInstance.transparentBackgroundView.drawToBitmap()

    val combinedBmp = BitmapUtilities.overlay(bmp2, bmp)

    val bmps: Bitmap?

    val matrix = Matrix()
    matrix.setRotate(outerCanvasInstance.getCurrentRotation())

    bmps = Bitmap.createBitmap(combinedBmp, 0, 0, combinedBmp.width, combinedBmp.height, matrix, false)

    if (gridWasEnabled) {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.showGrid()
    }

    return bmps
}