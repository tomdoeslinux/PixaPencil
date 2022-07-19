package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.clear

fun CanvasActivity.CanvasCommandsHelper.clearCanvas() {
    baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.clear()
    baseReference.viewModel.bitmapActionData.clear()
}