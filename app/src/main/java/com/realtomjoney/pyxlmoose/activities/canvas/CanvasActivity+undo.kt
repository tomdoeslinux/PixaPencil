package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedUndo() {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}