package com.therealbluepandabear.pyxlmoose.customviews.pixelgridview

var undoActionCompleted = true

fun PixelGridView.extendedUndo() {
    if (undoActionCompleted) {
        undoActionCompleted = false

        if (bitmapActionData.size > 0) {
            for ((key, value) in bitmapActionData.last().actionData.distinctBy { it.coordinates }) {
                pixelGridViewBitmap.setPixel(key.x, key.y, value)
            }

            undoStack.add(bitmapActionData.last())

            invalidate()
            bitmapActionData.removeLast()
        }

        undoActionCompleted = true

        caller.onUndoActionCompleted(undoStack, bitmapActionData)
    }
}