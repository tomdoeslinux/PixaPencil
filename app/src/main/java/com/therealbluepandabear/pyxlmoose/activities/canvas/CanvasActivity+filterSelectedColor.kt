package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.utility.ColorFilterUtilities

fun filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) {
        setPixelColor(ColorFilterUtilities.blendColor(getSelectedColor(), color, ratio))
    } else {
        setPixelColor(ColorFilterUtilities.blendColor(getSelectedColor(), color, ratio))
    }
}

