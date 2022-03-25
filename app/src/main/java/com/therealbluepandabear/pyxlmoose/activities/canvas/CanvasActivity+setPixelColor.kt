package com.therealbluepandabear.pyxlmoose.activities.canvas

fun setPixelColor(color: Int) {
    if (isPrimaryColorSelected) {
        setPrimaryPixelColor(color)
    } else {
        setSecondaryPixelColor(color)
    }
}
