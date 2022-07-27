package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.FlipValue

/**
 * There is a bug in which when you rotate a view 180 degrees, its drop shadow disappears.
 * This has been reported to Google but it hasn't been fixed in some compat libraries.
 * Hopefully it gets fixed in other libraries soon, but for now we can simply rotate it by 179.9 degrees and it seems to fix it.
 * The difference isn't noticeable.
 */

fun CanvasActivity.flip(flipValue: FlipValue) {
    if (viewModel.flipMatrix.isEmpty() || viewModel.flipMatrix.last() != flipValue) {
        viewModel.flipMatrix.add(flipValue)
    }

    if (flipValue == FlipValue.Horizontal) {
        if (binding.activityCanvasCardView.rotationY == 179.9f) {
            binding.activityCanvasCardView.rotationY = 0f
            viewModel.flipMatrix.removeLast()
        } else {
            binding.activityCanvasCardView.rotationY = 179.9f
        }
    } else {
        if (binding.activityCanvasCardView.rotationX == 179.9f) {
            binding.activityCanvasCardView.rotationX = 0f
            viewModel.flipMatrix.removeLast()
        } else {
            binding.activityCanvasCardView.rotationX = 179.9f
        }
    }
}