package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.extensions.disable
import com.therealbluepandabear.pyxlmoose.models.BitmapAction

fun extendedOnRedoActionCompleted(undoStack: List<BitmapAction>) {
    if (undoStack.isEmpty()) {
        menu.findItem(R.id.redo).disable()
    }
}