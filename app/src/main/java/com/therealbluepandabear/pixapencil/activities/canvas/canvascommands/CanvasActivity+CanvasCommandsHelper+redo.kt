package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.menu
import com.therealbluepandabear.pixapencil.activities.canvas.onactioncompleted.onRedoActionCompleted
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.CanvasCommandsHelper.redo() {
    if (viewModelReference.undoStack.size > 0) {
        menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()

        for (obj in viewModelReference.undoStack.last().actionData.distinctBy {
            it.coordinates
        }) {
            pixelGridViewInstance.pixelGridViewBitmap.setPixel(obj.coordinates, obj.colorSet)
        }

        pixelGridViewInstance.invalidate()

        viewModelReference.bitmapActionData.add(viewModelReference.undoStack.last())
        viewModelReference.undoStack.removeLast()

        onRedoActionCompleted(viewModelReference.undoStack)
    }
}