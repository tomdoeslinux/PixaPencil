package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.resetZoom() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX = 1f
        scaleY = 1f
    }

    pixelGridViewInstance.invalidate()
}