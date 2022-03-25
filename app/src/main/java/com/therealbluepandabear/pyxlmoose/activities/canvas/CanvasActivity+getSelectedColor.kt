package com.therealbluepandabear.pyxlmoose.activities.canvas

fun getSelectedColor(): Int {
    return if (isPrimaryColorSelected) {
        primaryColor
    } else secondaryColor
}