package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.setOriginalCoordinates() {
    if (originalX == null) {
        originalX = binding.activityCanvasCardView.x
    }

    if (originalY == null) {
        originalY = binding.activityCanvasCardView.y
    }
}