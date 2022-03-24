package com.realtomjoney.pyxlmoose.activities.canvas

fun lineToolOnActionUp() {
    lineOrigin = null
    lineMode_hasLetGo = true

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
    )
}