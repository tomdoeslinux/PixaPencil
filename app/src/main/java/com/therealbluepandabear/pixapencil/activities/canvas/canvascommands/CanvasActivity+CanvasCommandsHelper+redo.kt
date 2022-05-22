package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onactioncompleted.onRedoActionCompleted
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.CanvasCommandsHelper.redo() {
    if (baseReference.viewModel.undoStack.size > 0) {
        baseReference.menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()

        for (obj in baseReference.viewModel.undoStack.last().actionData.distinctBy {
            it.coordinates
        }) {
            pixelGridViewInstance.pixelGridViewBitmap.setPixel(obj.coordinates, obj.colorSet)
        }

        pixelGridViewInstance.invalidate()

        baseReference.viewModel.bitmapActionData.add(baseReference.viewModel.undoStack.last())
        baseReference.viewModel.undoStack.removeLast()

        baseReference.onRedoActionCompleted(baseReference.viewModel.undoStack)
    }
}