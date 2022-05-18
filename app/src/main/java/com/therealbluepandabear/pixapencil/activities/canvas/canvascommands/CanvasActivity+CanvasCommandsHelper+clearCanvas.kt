package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.clearCanvas() {
    pixelGridViewInstance.apply {
        for (i_1 in 0 until pixelGridViewBitmap.width) {
            for (i_2 in 0 until pixelGridViewBitmap.height) {
                pixelGridViewBitmap.setPixel(Coordinates(i_1, i_2), Color.TRANSPARENT)
            }
        }
        invalidate()
        viewModelReference.bitmapActionData.clear()
    }
}