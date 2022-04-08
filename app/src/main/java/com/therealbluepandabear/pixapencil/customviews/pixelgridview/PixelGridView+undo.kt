package com.therealbluepandabear.pixapencil.customviews.pixelgridview

fun PixelGridView.extendedUndo() {
    if (bitmapActionData.size > 0) {
        for ((key, value) in bitmapActionData.last().actionData.distinctBy { it.coordinates }) {
            pixelGridViewBitmap.setPixel(key.x, key.y, value)
        }

        undoStack.add(bitmapActionData.last())

        invalidate()
        bitmapActionData.removeLast()
    }

    caller.onUndoActionCompleted(undoStack, bitmapActionData)
}