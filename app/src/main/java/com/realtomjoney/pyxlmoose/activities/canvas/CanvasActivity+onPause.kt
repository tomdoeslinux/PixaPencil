package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedOnPause() {
    currentBackground = null
    canvasStates.clear()
    deletedCanvasStates.clear()
    currentTool = Tools.PENCIL_TOOL
    gridEnabled = false
    gridDisabledFromZoomOut = false
    pixelsTappedAsXYPosition.clear()
}