package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.disable

fun extendedUndo() {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.isEmpty()) {
        menu.findItem(R.id.undo).disable()
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}