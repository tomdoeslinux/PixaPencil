package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.enable

fun zoomIn() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX += zoomIncrement
        scaleY += zoomIncrement
    }

    menu.findItem(R.id.zoom_out).enable()

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()
}