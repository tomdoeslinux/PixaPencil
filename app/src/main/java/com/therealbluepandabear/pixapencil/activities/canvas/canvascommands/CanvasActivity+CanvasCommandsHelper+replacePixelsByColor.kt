package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.iterate
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.CanvasCommandsHelper.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    baseReference.viewModel.saved = false

    baseReference.viewModel.currentBitmapAction = BitmapAction(mutableListOf())

    pixelGridViewInstance.pixelGridViewBitmap.iterate {
        if (pixelGridViewInstance.pixelGridViewBitmap.getPixel(it) == colorToFind) {
            overrideSetPixel(it, colorToReplace, true, ignoreSymmetry = true, ignoreDither = true, ignoreShadingMap = true)
        }
    }

    baseReference.viewModel.bitmapActionData.add(baseReference.viewModel.currentBitmapAction!!)
    baseReference.viewModel.currentBitmapAction = null

    pixelGridViewInstance.invalidate()
}