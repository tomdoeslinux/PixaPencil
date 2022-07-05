package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.addMenuProvider.ZOOM_INCREMENT
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import java.math.RoundingMode

fun CanvasActivity.onZoomOutOptionsItemSelected() {
    val canZoomOut = outerCanvasInstance.cardViewParent.scaleX - ZOOM_INCREMENT > ZOOM_INCREMENT

    if (canZoomOut) {
        outerCanvasInstance.cardViewParent.scaleX -= ZOOM_INCREMENT
        outerCanvasInstance.cardViewParent.scaleY -= ZOOM_INCREMENT
    }

    val roundedZoom = ZOOM_INCREMENT.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

    if (outerCanvasInstance.cardViewParent.scaleX - ZOOM_INCREMENT < roundedZoom) {
        menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).disable()
    }

    pixelGridViewInstance.invalidate()
}