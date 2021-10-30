package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.View

fun CanvasActivity.extendedOnColourTapped(colour: Int, it: View) {
    setPixelColour(colour)

    isSelected = if (!isSelected) {
        updateColourSelectedIndicator(it)
        true
    } else {
        updateColourSelectedIndicator(it)
        false
    }
}