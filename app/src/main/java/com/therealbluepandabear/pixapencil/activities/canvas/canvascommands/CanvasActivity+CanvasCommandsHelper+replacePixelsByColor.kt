package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.doAddLast
import com.therealbluepandabear.pixapencil.extensions.replacePixelsByColor
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.CanvasCommandsHelper.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    baseReference.viewModel.saved = false

    baseReference.viewModel.currentBitmapAction = BitmapAction(mutableListOf())

    baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.replacePixelsByColor(colorToFind, colorToReplace) {
        overrideSetPixel(it, colorToReplace, true, ignoreSymmetry = true, ignoreDither = true, ignoreShadingMap = true)
    }

    baseReference.viewModel.undoStack.doAddLast(baseReference.viewModel.currentBitmapAction!!)
    baseReference.viewModel.currentBitmapAction = null

    baseReference.binding.activityCanvasPixelGridView.invalidate()
}