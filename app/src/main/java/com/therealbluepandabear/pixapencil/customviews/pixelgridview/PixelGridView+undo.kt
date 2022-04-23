package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

fun PixelGridView.extendedUndo() {
    if (bitmapActionData.size > 0) {
        for ((key, value) in bitmapActionData.last().actionData.distinctBy { it.coordinates }) {
            pixelGridViewBitmap.setPixel(Coordinates(key.x, key.y), value)
        }

        undoStack.add(bitmapActionData.last())

        invalidate()
        bitmapActionData.removeLast()
    }

    caller.onUndoActionCompleted(undoStack, bitmapActionData)
}