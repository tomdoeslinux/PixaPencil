package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.utility.BinaryPreviewStateSwitcher

fun lineToolOnActionUp() {
    lineOrigin = null
    lineMode_hasLetGo = false
    first = true

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
    )

    BinaryPreviewStateSwitcher.clear()
}