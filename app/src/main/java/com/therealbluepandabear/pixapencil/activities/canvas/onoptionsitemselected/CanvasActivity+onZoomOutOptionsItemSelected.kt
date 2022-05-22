package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import java.math.RoundingMode

fun CanvasActivity.onZoomOutOptionsItemSelected() {
    outerCanvasInstance.cardViewParent.apply {
        val canZoomOut = outerCanvasInstance.cardViewParent.scaleX - zoomIncrement > zoomIncrement

        if (canZoomOut) {
            scaleX -= zoomIncrement
            scaleY -= zoomIncrement
        }

        val roundedZoom = zoomIncrement.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        if (scaleX - zoomIncrement < roundedZoom) {
            menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).disable()
        }
    }

    pixelGridViewInstance.invalidate()
}