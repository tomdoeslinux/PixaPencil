package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.iterate
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

// could be done in a better fashion

fun CanvasActivity.CanvasCommandsHelper.clearCanvas() {
    pixelGridViewInstance.pixelGridViewBitmap.iterate {
        for (i_2 in 0 until pixelGridViewInstance.pixelGridViewBitmap.height) {
            pixelGridViewInstance.pixelGridViewBitmap.setPixel(it, Color.TRANSPARENT)
        }
    }

    pixelGridViewInstance.invalidate()
    baseReference.viewModel.bitmapActionData.clear()
}