package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.onResetZoomOptionsItemSelected() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX = 1f
        scaleY = 1f
    }

    pixelGridViewInstance.invalidate()
}