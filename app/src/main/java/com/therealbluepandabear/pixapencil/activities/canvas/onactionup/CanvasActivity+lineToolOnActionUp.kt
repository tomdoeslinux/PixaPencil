package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.lineMode_hasLetGo
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.first
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.lineOrigin
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