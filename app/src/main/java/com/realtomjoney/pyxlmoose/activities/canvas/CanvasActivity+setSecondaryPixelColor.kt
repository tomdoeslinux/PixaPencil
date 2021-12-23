package com.realtomjoney.pyxlmoose.activities.canvas

fun CanvasActivity.extendedSetSecondaryPixelColor(color: Int) {
    secondaryColor = color
    binding.activityCanvasColorSecondaryView.setBackgroundColor(color)
}