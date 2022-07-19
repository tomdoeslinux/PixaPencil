package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import android.annotation.SuppressLint
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

@SuppressLint("ClickableViewAccessibility")
fun CanvasActivity.removeOnTouchListener() {
    binding.activityCanvasMoveView.setOnTouchListener { _, _ -> false }
}
