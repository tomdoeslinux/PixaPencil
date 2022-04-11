package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

fun lineToolOnActionUp() {
    lineOrigin = null
    lineMode_hasLetGo = false
    first = true

    pixelGridViewInstance.bitmapActionData.add(
        pixelGridViewInstance.currentBitmapAction!!
    )

    BinaryPreviewStateSwitcher.clear()
}