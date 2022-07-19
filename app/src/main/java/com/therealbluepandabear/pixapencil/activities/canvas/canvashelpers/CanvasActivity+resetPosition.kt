package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.resetPosition() {
    binding.activityCanvasCardView.x = originalX!!
    binding.activityCanvasCardView.y = originalY!!
    binding.root.invalidate()
}