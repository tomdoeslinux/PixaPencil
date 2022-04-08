package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

fun lineToolOnActionUp() {
    lineOrigin = null
    lineMode_hasLetGo = false
    first = true

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!
    )

    BinaryPreviewStateSwitcher.clear()
}