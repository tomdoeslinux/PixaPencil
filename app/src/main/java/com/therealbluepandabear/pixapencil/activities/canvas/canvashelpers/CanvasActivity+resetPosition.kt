package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.resetPosition() {
    binding.activityCanvasCardView.x = originalX ?: 0f
    binding.activityCanvasCardView.y = originalY ?: 0f
    binding.root.invalidate()
}