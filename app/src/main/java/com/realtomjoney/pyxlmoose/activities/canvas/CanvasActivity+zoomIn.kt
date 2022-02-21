package com.realtomjoney.pyxlmoose.activities.canvas

fun zoomIn() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX += zoomIncrement
        scaleY += zoomIncrement
    }
}