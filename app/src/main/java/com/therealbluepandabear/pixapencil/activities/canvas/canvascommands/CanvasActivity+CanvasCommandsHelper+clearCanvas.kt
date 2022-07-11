package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.clear
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.CanvasCommandsHelper.clearCanvas() {
    pixelGridViewInstance.pixelGridViewBitmap.clear()
    baseReference.viewModel.bitmapActionData.clear()
}