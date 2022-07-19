package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.setPixelColor(color: Int) {
    if (binding.activityCanvasColorSwitcherView.getIsPrimarySelected()) {
        viewModel.primaryColor = color
        viewModel.isPrimaryColorSelected = true
        binding.activityCanvasColorSwitcherView.setPrimaryColor(viewModel.primaryColor)
    } else {
        viewModel.secondaryColor = color
        viewModel.isPrimaryColorSelected = false
        binding.activityCanvasColorSwitcherView.setSecondaryColor(viewModel.secondaryColor)
    }
}
