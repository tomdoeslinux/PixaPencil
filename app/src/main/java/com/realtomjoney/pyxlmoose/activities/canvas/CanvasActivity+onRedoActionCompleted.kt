package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.disable
import com.realtomjoney.pyxlmoose.models.BitmapAction

fun extendedOnRedoActionCompleted(undoStack: List<BitmapAction>) {
    if (undoStack.isEmpty()) {
        menu.findItem(R.id.redo).disable()
    }
}