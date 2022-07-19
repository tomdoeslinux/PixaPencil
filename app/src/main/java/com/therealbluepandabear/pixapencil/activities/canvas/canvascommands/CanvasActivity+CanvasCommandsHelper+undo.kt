package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onactioncompleted.extendedOnUndoActionCompleted
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.undo() {
    if (baseReference.viewModel.bitmapActionData.size > 0) {
        for ((key, value) in baseReference.viewModel.bitmapActionData.last().actionData.distinctBy {
            it.coordinates
        }) {
            baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.setPixel(Coordinates(key.x, key.y), value)
        }

        baseReference.viewModel.redoStack.add(baseReference.viewModel.bitmapActionData.last())

        baseReference.binding.activityCanvasPixelGridView.invalidate()
        baseReference.viewModel.bitmapActionData.removeLast()
    }

    baseReference.extendedOnUndoActionCompleted(baseReference.viewModel.redoStack, baseReference.viewModel.bitmapActionData)
}