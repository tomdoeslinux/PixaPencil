package com.therealbluepandabear.pixapencil.activities.canvas

fun setSecondaryPixelColor(color: Int) {
    secondaryColor = color
    binding.activityCanvasColorSecondaryView.setBackgroundColor(color)
}