package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.menu
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun PixelGridView.extendedRedo() {
    if (undoStack.size > 0) {
        menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()

        for (obj in undoStack.last().actionData.distinctBy {
            it.coordinates
        }) {
            pixelGridViewBitmap.setPixel(obj.coordinates, obj.colorSet)
        }

        invalidate()

        pixelGridViewInstance.bitmapActionData.add(undoStack.last())
        undoStack.removeLast()

        caller.onRedoActionCompleted(undoStack)
    }
}