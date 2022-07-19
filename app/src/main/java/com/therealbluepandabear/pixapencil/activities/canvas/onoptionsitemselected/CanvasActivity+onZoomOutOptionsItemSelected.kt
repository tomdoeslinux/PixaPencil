package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu.ZOOM_INCREMENT
import com.therealbluepandabear.pixapencil.extensions.disable
import java.math.RoundingMode

fun CanvasActivity.onZoomOutOptionsItemSelected() {
    val canZoomOut = binding.activityCanvasCardView.scaleX - ZOOM_INCREMENT > ZOOM_INCREMENT

    if (canZoomOut) {
        binding.activityCanvasCardView.scaleX -= ZOOM_INCREMENT
        binding.activityCanvasCardView.scaleY -= ZOOM_INCREMENT
    }

    val roundedZoom = ZOOM_INCREMENT.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

    if (binding.activityCanvasCardView.scaleX - ZOOM_INCREMENT < roundedZoom) {
        menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).disable()
    }

    binding.activityCanvasPixelGridView.invalidate()
}