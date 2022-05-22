package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.onResetZoomOptionsItemSelected() {
    outerCanvasInstance.cardViewParent.scaleX = 1f
    outerCanvasInstance.cardViewParent.scaleY = 1f

    pixelGridViewInstance.invalidate()
}