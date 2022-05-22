package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.setPrimaryPixelColor(color: Int) {
    primaryColor = color
    binding.activityCanvasColorPrimaryView.setBackgroundColor(color)
}