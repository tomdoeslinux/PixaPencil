package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.cindx
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.polygonCoordinates
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun onUndoOptionsItemSelected() {
    pixelGridViewInstance.undo()

    pixelGridViewInstance.currentBitmapAction = null
    polygonCoordinates.clear()
    cindx = 0
}