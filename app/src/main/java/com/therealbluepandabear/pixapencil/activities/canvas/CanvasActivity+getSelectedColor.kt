package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.getSelectedColor(): Int {
    return if (binding.activityCanvasColorSwitcherView.getIsPrimarySelected()) {
        viewModel.primaryColor
    } else {
        viewModel.secondaryColor
    }
}