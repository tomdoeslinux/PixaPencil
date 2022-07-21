package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.RotationValue

fun CanvasActivity.rotate(rotationValue: RotationValue, animate: Boolean = false) {
    rotate(rotationValue.degrees, rotationValue.clockwise, animate)
}

fun CanvasActivity.rotate(degrees: Int, clockwise: Boolean = true, animate: Boolean = false) {
    val rotationAmount = if (clockwise) {
        (binding.activityCanvasCardView.rotation + degrees)
    } else {
        (binding.activityCanvasCardView.rotation - degrees)
    }

    if (animate) {
        binding.activityCanvasCardView
            .animate()
            .rotation(rotationAmount)
    } else {
        binding.activityCanvasCardView.rotation = rotationAmount
    }

    viewModel.currentRotation = rotationAmount.toInt()
}