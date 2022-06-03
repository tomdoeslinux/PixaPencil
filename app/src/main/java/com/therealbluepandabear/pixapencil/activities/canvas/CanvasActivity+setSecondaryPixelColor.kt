package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.setSecondaryPixelColor(color: Int) {
    viewModel.secondaryColor = color
    binding.activityCanvasColorSecondaryView.setBackgroundColor(color)
}