package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.lineMode_hasLetGo
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.first
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.lineOrigin
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

fun CanvasActivity.lineToolOnActionUp() {
    lineOrigin = null
    lineMode_hasLetGo = false
    first = true

    viewModel.bitmapActionData.add(
        viewModel.currentBitmapAction!!
    )

    BinaryPreviewStateSwitcher.clear()
}