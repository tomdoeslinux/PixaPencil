package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.RotationValue

fun CanvasActivity.rotate(rotationValue: RotationValue) {
    rotate(rotationValue.degrees, rotationValue.clockwise)
}

fun CanvasActivity.rotate(degrees: Float, clockwise: Boolean = true) {
    val rotationAmount = if (clockwise) {
        (binding.activityCanvasCardView.rotation + degrees)
    } else {
        (binding.activityCanvasCardView.rotation - degrees)
    }

    binding.activityCanvasCardView.rotation = rotationAmount

    viewModel.currentRotation = rotationAmount
}