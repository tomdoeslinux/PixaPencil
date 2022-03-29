package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun extendedOnUndoActionCompleted(
    undoStack: List<BitmapAction>,
    bitmapActionData: List<BitmapAction>
) {
    if (bitmapActionData.isEmpty()) {
        menu.findItem(R.id.undo).disable()
    }

    if (undoStack.isNotEmpty()) {
        menu.findItem(R.id.redo).enable()
    }
}