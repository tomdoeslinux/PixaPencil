package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.BitmapUtilities

var gridWasEnabled = false

fun CanvasActivity.getCoverImageBitmap(): Bitmap {
    if (pixelGridViewInstance.gridEnabled) {
        pixelGridViewInstance.gridEnabled = false
        pixelGridViewInstance.invalidate()
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
        pixelGridViewInstance.gridEnabled = false
        pixelGridViewInstance.invalidate()
    }

    return bmps
}