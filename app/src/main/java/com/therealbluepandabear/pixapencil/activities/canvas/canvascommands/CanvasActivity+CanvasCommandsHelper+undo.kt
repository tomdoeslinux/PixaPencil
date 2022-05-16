package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onactioncompleted.extendedOnUndoActionCompleted
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.undo() {
    if (viewModelReference.bitmapActionData.size > 0) {
        for ((key, value) in viewModelReference.bitmapActionData.last().actionData.distinctBy {
            it.coordinates
        }) {
            pixelGridViewInstance.pixelGridViewBitmap.setPixel(Coordinates(key.x, key.y), value)
        }

        viewModelReference.undoStack.add(viewModelReference.bitmapActionData.last())

        pixelGridViewInstance.invalidate()
        viewModelReference.bitmapActionData.removeLast()
    }

    extendedOnUndoActionCompleted(viewModelReference.undoStack, viewModelReference.bitmapActionData)
}