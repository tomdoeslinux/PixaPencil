package com.realtomjoney.pyxlmoose.activities.canvas

fun CanvasActivity.extendedSetPrimaryPixelColor(color: Int) {
    primaryColor = color
    binding.activityCanvasColorPrimaryView.setBackgroundColor(color)
}