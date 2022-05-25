package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.enums.OverlayType
import com.therealbluepandabear.pixapencil.extensions.isRect
import com.therealbluepandabear.pixapencil.extensions.isWidthLarger
import com.therealbluepandabear.pixapencil.extensions.overlay
import com.therealbluepandabear.pixapencil.extensions.rotate
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

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
    return transparentBackgroundBitmap.overlay(primaryBitmap)
}

fun CanvasActivity.getCoverImageBitmap(): Bitmap {
    disableGridIfNeeded()
    var coverBitmap = createBasicCoverBitmap()

    if (coverBitmap.isRect()) {
        val bitmapBaseDimension = if (coverBitmap.isWidthLarger()) {
            outerCanvasInstance.fragmentHost.width
        } else {
            outerCanvasInstance.fragmentHost.height
        }

        val bitmap = Bitmap.createBitmap(
            bitmapBaseDimension,
            bitmapBaseDimension,
            Bitmap.Config.ARGB_8888
        )
        bitmap.eraseColor(Color.WHITE)
        coverBitmap = bitmap.overlay(coverBitmap, OverlayType.CenterSecond)
    }

    val bmps: Bitmap?
    bmps = coverBitmap.rotate(outerCanvasInstance.getCurrentRotation().toInt())

    enableGridIfNeeded()

    return bmps
}