package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.zoomIn() {
    outerCanvasInstance.cardViewParent.apply {
        scaleX += zoomIncrement
        scaleY += zoomIncrement
    }

    menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).enable()

    pixelGridViewInstance.invalidate()
}