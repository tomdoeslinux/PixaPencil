package com.realtomjoney.pyxlmoose.activities.canvas

fun zoomOut() {
    outerCanvasInstance.cardViewParent.apply {
        if (outerCanvasInstance.cardViewParent.scaleX - zoomIncrement > zoomIncrement) {
            scaleX -= zoomIncrement
            scaleY -= zoomIncrement
        }
    }
}