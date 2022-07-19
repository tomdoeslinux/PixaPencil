package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.rotate

fun CanvasActivity.drawPixelGridViewBitmap(): Bitmap {
    return binding.activityCanvasPixelGridView.pixelGridViewBitmap
        .rotate(binding.activityCanvasCardView.rotation.toInt())
}