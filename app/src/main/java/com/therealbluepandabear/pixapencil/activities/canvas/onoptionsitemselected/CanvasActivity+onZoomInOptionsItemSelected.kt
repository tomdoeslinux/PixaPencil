package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu.ZOOM_INCREMENT
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable

fun CanvasActivity.onZoomInOptionsItemSelected() {
    binding.activityCanvasCardView.scaleX += ZOOM_INCREMENT
    binding.activityCanvasCardView.scaleY += ZOOM_INCREMENT

    if (binding.activityCanvasCardView.scaleX + ZOOM_INCREMENT > 1_000_000) {
        menu.findItem(R.id.activityCanvasTopAppMenu_zoom_in_item).disable()
    } else {
        menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).enable()
    }

    binding.activityCanvasPixelGridView.invalidate()
}