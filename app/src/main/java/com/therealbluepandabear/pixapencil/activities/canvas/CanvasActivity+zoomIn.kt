package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.enable

fun zoomIn() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX += zoomIncrement
        scaleY += zoomIncrement
    }

    menu.findItem(R.id.appMenu_zoom_out_item).enable()

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()
}