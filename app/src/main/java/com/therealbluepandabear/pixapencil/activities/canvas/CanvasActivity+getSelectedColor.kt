package com.therealbluepandabear.pixapencil.activities.canvas

fun getSelectedColor(): Int {
    return if (isPrimaryColorSelected) {
        primaryColor
    } else secondaryColor
}