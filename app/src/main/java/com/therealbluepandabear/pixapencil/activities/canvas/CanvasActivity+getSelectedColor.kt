package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.getSelectedColor(): Int {
    return if (isPrimaryColorSelected) {
        viewModel.primaryColor
    } else viewModel.secondaryColor
}