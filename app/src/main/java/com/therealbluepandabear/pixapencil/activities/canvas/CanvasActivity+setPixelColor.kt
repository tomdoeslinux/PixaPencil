package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.setPixelColor(color: Int) {
    if (isPrimaryColorSelected) {
        setPrimaryPixelColor(color)
    } else {
        setSecondaryPixelColor(color)
    }
}
