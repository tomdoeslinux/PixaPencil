package com.therealbluepandabear.pyxlmoose.activities.canvas

fun resetZoom() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX = 1f
        scaleY = 1f
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()
}