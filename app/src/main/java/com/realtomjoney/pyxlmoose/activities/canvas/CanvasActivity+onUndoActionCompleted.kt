package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.disable
import com.realtomjoney.pyxlmoose.extensions.enable
import com.realtomjoney.pyxlmoose.models.BitmapAction

fun CanvasActivity.extendedOnUndoActionCompleted(undoStack: List<BitmapAction>, bitmapActionData: List<BitmapAction>) {
    if (bitmapActionData.isEmpty()) {
        menu.findItem(R.id.undo).disable()
    }

    if (undoStack.isNotEmpty()) {
        menu.findItem(R.id.redo).enable()
    }
}