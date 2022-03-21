package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.utility.ColorFilterUtilities

fun filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) {
        setPixelColor(ColorFilterUtilities.blendColor(getSelectedColor(), color, ratio))
    } else {
        setPixelColor(ColorFilterUtilities.blendColor(getSelectedColor(), color, ratio))
    }
}

