package com.therealbluepandabear.pyxlmoose.activities.canvas

fun setSecondaryPixelColor(color: Int) {
    secondaryColor = color
    binding.activityCanvasColorSecondaryView.setBackgroundColor(color)
}