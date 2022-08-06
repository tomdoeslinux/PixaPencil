package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onactioncompleted.onRedoActionCompleted
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.extensions.setPixel

fun CanvasActivity.CanvasCommandsHelper.redo() {
    if (baseReference.viewModel.redoStack.size > 0) {
        baseReference.menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()

        for (obj in baseReference.viewModel.redoStack.last().actionData.distinctBy {
            it.coordinates
        }) {
            baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.setPixel(obj.coordinates, obj.colorSet)
        }

        baseReference.binding.activityCanvasPixelGridView.invalidate()

        baseReference.viewModel.undoStack.push(baseReference.viewModel.redoStack.peek())
        baseReference.viewModel.redoStack.pop()

        baseReference.onRedoActionCompleted(baseReference.viewModel.redoStack)
    }
}