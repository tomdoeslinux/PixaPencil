package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.filterBitmap
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.CanvasCommandsHelper.applyBitmapFilter(filterLambda: (Int) -> Int) {
    baseReference.binding.activityCanvasPixelGridView.shadingMap.clear()
    baseReference.viewModel.saved = false
    baseReference.viewModel.currentBitmapAction = BitmapAction(mutableListOf())

    baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.filterBitmap(filterLambda) { coordinates, color ->
        overrideSetPixel(coordinates, color, ignoreBrush = true, ignoreSymmetry = true, ignoreDither = true)
    }

    baseReference.viewModel.bitmapActionData.add(baseReference.viewModel.currentBitmapAction!!)
    baseReference.viewModel.currentBitmapAction = null

    baseReference.binding.activityCanvasPixelGridView.invalidate()
}