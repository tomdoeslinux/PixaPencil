package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.BitmapUtilities
import com.therealbluepandabear.pixapencil.utility.OverlayType

var gridWasEnabled = false

fun disableGridIfNeeded() {
    if (pixelGridViewInstance.gridEnabled) {
        pixelGridViewInstance.gridEnabled = false
        pixelGridViewInstance.invalidate()
        gridWasEnabled = true
    }
}

fun enableGridIfNeeded() {
    if (gridWasEnabled) {
        pixelGridViewInstance.gridEnabled = false
        pixelGridViewInstance.invalidate()
    }
}

fun CanvasActivity.createBasicCoverBitmap(): Bitmap {
    val primaryBitmap = outerCanvasInstance.fragmentHost.drawToBitmap()
    val transparentBackgroundBitmap = outerCanvasInstance.transparentBackgroundView.drawToBitmap()
    return BitmapUtilities.overlay(transparentBackgroundBitmap, primaryBitmap)
}

fun CanvasActivity.getCoverImageBitmap(): Bitmap {
    disableGridIfNeeded()
    var coverBitmap = createBasicCoverBitmap()

    if (coverBitmap.width > coverBitmap.height) {
        val bitmap = Bitmap.createBitmap(outerCanvasInstance.fragmentHost.width, outerCanvasInstance.fragmentHost.width, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.WHITE)
        coverBitmap = BitmapUtilities.overlay(bitmap, coverBitmap, OverlayType.CenterSecond)
    } else if (coverBitmap.height > coverBitmap.width) {
        val bitmap = Bitmap.createBitmap(outerCanvasInstance.fragmentHost.height, outerCanvasInstance.fragmentHost.height, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.WHITE)
        coverBitmap = BitmapUtilities.overlay(bitmap, coverBitmap, OverlayType.CenterSecond)
    }

    val bmps: Bitmap?

    val matrix = Matrix()
    matrix.setRotate(outerCanvasInstance.getCurrentRotation())

    bmps = Bitmap.createBitmap(coverBitmap, 0, 0, coverBitmap.width, coverBitmap.height, matrix, false)

    enableGridIfNeeded()

    return bmps
}