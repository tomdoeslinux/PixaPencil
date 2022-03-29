package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.disable
import java.math.RoundingMode

fun zoomOut() {
    outerCanvasInstance.cardViewParent.apply {
        val canZoomOut = outerCanvasInstance.cardViewParent.scaleX - zoomIncrement > zoomIncrement

        if (canZoomOut) {
            scaleX -= zoomIncrement
            scaleY -= zoomIncrement
        }

        if (scaleX - zoomIncrement < zoomIncrement.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()) {
            menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).disable()
        }
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()
}