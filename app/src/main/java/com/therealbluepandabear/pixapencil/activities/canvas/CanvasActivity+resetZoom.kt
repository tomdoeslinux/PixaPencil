package com.therealbluepandabear.pixapencil.activities.canvas

fun resetZoom() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX = 1f
        scaleY = 1f
    }

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.invalidate()
}