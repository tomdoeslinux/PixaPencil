package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.onResetZoomOptionsItemSelected() {
    binding.activityCanvasCardView.scaleX = 1f
    binding.activityCanvasCardView.scaleY = 1f

    binding.activityCanvasPixelGridView.invalidate()
}