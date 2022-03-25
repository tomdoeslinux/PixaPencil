package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.disable
import com.realtomjoney.pyxlmoose.extensions.enable

fun extendedUndo() {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.isEmpty()) {
        menu.findItem(R.id.undo).disable()
    }

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.undoStack.size > 0) {
        menu.findItem(R.id.redo).enable()
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}