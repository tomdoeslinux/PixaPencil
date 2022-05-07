package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.cindx
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.polygonCoordinates
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun undo() {
    pixelGridViewInstance.undo()

    pixelGridViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}