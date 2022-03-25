package com.therealbluepandabear.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.activities.canvas.menu
import com.therealbluepandabear.pyxlmoose.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pyxlmoose.extensions.enable

fun PixelGridView.extendedRedo() {
    /**
     * Added extra check here just to be safe that we're not operating on an empty data set.
     */

    if (undoStack.size > 0) {
        menu.findItem(R.id.undo).enable()

        for (obj in undoStack.last().actionData.distinctBy { it.coordinates }) {
            pixelGridViewBitmap.setPixel(obj.coordinates.x, obj.coordinates.y, obj.colorSet)
        }

        invalidate()

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(undoStack.last())
        undoStack.removeLast()

        caller.onRedoActionCompleted(undoStack)
    }
}