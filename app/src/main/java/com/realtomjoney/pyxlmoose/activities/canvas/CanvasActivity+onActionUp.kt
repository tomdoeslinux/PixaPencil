package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedOnActionUp() {
    if (currentTool == Tools.LINE_TOOL) {
        lineOrigin = null
        lineMode_hasLetGo = true
        rectangleMode_hasLetGo = true
    } else if (currentTool == Tools.RECTANGLE_TOOL || currentTool == Tools.OUTLINED_RECTANGLE_TOOL) {
        rectangleOrigin = null
        rectangleMode_hasLetGo = true
    } else {
        canvasInstance.myCanvasViewInstance.bitmapActionData.add(canvasInstance.myCanvasViewInstance.currentBitmapAction!!)
        canvasInstance.myCanvasViewInstance.currentBitmapAction = null

        canvasInstance.myCanvasViewInstance.prevX = null
        canvasInstance.myCanvasViewInstance.prevY = null
    }
}