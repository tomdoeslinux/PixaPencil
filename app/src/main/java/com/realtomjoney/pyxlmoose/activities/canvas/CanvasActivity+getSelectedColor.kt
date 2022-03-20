package com.realtomjoney.pyxlmoose.activities.canvas

fun getSelectedColor(): Int {
    return if (isPrimaryColorSelected) {
        primaryColor
    } else secondaryColor
}