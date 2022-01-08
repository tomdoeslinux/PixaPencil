package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedOnActionUp() {
    lineMode_hasLetGo = true
    lineOrigin = null

    if (currentTool != Tools.LINE_TOOL) canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
}