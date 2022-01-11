package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedOnActionUp() {
    lineMode_hasLetGo = true
    rectangleMode_hasLetGo = true
    lineOrigin = null
    rectangleOrigin = null

    pixelsTappedAsXYPosition.clear()

    if (currentTool != Tools.LINE_TOOL && currentTool != Tools.RECTANGLE_TOOL) canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
}