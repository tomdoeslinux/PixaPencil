package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.extensions.disable
import com.therealbluepandabear.pyxlmoose.extensions.enable
import com.therealbluepandabear.pyxlmoose.models.BitmapAction

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