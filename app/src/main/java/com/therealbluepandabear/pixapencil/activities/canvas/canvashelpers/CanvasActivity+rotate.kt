package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.RotationValue
import kotlin.math.ceil

fun CanvasActivity.rotate(rotationValue: RotationValue) {
    rotate(rotationValue.degrees, rotationValue.clockwise)
}

fun CanvasActivity.rotate(degrees: Float, clockwise: Boolean = true) {
    val rotationAmount = if (clockwise || degrees == RotationValue.OneEighty.degrees) {
        (binding.activityCanvasCardView.rotation + degrees)
    } else {
        (binding.activityCanvasCardView.rotation - degrees)
    }

    val nR = ceil(rotationAmount) - 0.1f

    binding.activityCanvasCardView.rotation = nR
    viewModel.currentRotation = nR
}