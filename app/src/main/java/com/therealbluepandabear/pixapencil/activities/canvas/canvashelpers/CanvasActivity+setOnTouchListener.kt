package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.setOnTouchListener() {
    binding.activityCanvasMoveView.setOnTouchListener(onMoveViewTouchListener())
}
