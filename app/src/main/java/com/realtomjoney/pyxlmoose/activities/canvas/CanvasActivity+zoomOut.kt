package com.realtomjoney.pyxlmoose.activities.canvas

fun zoomOut() {
    outerCanvasInstance.cardViewParent.apply {
        val canZoomOut = outerCanvasInstance.cardViewParent.scaleX - zoomIncrement > zoomIncrement

        if (canZoomOut) {
            scaleX -= zoomIncrement
            scaleY -= zoomIncrement
        }
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()
}