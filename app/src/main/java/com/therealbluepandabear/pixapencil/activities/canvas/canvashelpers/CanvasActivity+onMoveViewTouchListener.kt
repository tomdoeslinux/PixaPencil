package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

@SuppressLint("ClickableViewAccessibility")
fun CanvasActivity.onMoveViewTouchListener(): View.OnTouchListener {
    return View.OnTouchListener { _, event ->
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dX = binding.activityCanvasCardView.x - event.rawX
                dY = binding.activityCanvasCardView.y - event.rawY
            }

            MotionEvent.ACTION_MOVE -> {
                binding.activityCanvasCardView.y = event.rawY + dY
                binding.activityCanvasCardView.x = event.rawX + dX
            }
        }

        binding.root.invalidate()
        true
    }
}