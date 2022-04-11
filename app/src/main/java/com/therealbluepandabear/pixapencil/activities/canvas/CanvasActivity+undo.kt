package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun extendedUndo() {
    pixelGridViewInstance.undo()

    pixelGridViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}