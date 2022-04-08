package com.therealbluepandabear.pixapencil.activities.canvas

fun extendedUndo() {
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.undo()

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}