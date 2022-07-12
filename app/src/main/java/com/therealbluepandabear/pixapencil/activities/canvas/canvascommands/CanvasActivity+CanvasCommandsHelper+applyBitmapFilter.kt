package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.filterBitmap
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.CanvasCommandsHelper.applyBitmapFilter(filterLambda: (Int) -> Int) {
    pixelGridViewInstance.shadingMap.clear()
    baseReference.viewModel.saved = false
    baseReference.viewModel.currentBitmapAction = BitmapAction(mutableListOf())

    pixelGridViewInstance.pixelGridViewBitmap.filterBitmap(filterLambda) { coordinates, color ->
        overrideSetPixel(coordinates, color, ignoreBrush = true, ignoreSymmetry = true)
    }

    baseReference.viewModel.bitmapActionData.add(baseReference.viewModel.currentBitmapAction!!)
    baseReference.viewModel.currentBitmapAction = null

    pixelGridViewInstance.invalidate()
}