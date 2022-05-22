package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.setSecondaryPixelColor(color: Int) {
    secondaryColor = color
    binding.activityCanvasColorSecondaryView.setBackgroundColor(color)
}