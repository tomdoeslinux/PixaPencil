package com.therealbluepandabear.pyxlmoose.activities.canvas

fun setPrimaryPixelColor(color: Int) {
    primaryColor = color
    binding.activityCanvasColorPrimaryView.setBackgroundColor(color)
}