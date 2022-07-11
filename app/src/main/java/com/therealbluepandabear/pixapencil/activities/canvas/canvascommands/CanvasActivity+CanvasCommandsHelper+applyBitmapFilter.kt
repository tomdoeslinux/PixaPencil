package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.iterate
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.CanvasCommandsHelper.applyBitmapFilter(filterLambda: (Int) -> Int) {
    pixelGridViewInstance.shadingMap.clear()
    baseReference.viewModel.saved = false
    baseReference.viewModel.currentBitmapAction = BitmapAction(mutableListOf())

    pixelGridViewInstance.pixelGridViewBitmap.iterate {
        val colorAtCoordinates = pixelGridViewInstance.pixelGridViewBitmap.getPixel(it)

        if (colorAtCoordinates != Color.TRANSPARENT) {
            val color = filterLambda(colorAtCoordinates)

            overrideSetPixel(it, color, ignoreBrush = true, ignoreSymmetry = true)
        }
    }


    baseReference.viewModel.bitmapActionData.add(baseReference.viewModel.currentBitmapAction!!)
    baseReference.viewModel.currentBitmapAction = null

    pixelGridViewInstance.invalidate()
}