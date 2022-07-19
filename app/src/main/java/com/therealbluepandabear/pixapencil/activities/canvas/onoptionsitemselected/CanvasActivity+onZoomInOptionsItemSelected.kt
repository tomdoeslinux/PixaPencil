package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu.ZOOM_INCREMENT
import com.therealbluepandabear.pixapencil.extensions.enable

fun CanvasActivity.onZoomInOptionsItemSelected() {
    binding.activityCanvasCardView.scaleX += ZOOM_INCREMENT
    binding.activityCanvasCardView.scaleY += ZOOM_INCREMENT
    
    menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).enable()

    binding.activityCanvasPixelGridView.invalidate()
}