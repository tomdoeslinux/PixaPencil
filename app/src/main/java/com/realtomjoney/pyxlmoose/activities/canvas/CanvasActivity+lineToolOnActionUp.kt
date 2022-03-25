package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.utility.BinaryPreviewStateSwitcher

fun lineToolOnActionUp() {
    lineOrigin = null
    lineMode_hasLetGo = false
    first = true

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
    )

    BinaryPreviewStateSwitcher.clear()
}