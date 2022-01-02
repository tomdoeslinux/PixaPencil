package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedOnPause() {
    currentBackground = null
    canvasStates.clear()
    currentTool = Tools.PENCIL_TOOL
    gridEnabled = false
    gridDisabledFromZoomOut = false
}