package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.setPrimaryPixelColor(color: Int) {
    viewModel.primaryColor = color
    binding.activityCanvasColorPrimaryView.setBackgroundColor(color)
}