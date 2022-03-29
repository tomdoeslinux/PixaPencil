package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities

fun filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) {
        setPixelColor(ColorFilterUtilities.blendColor(getSelectedColor(), color, ratio))
    } else {
        setPixelColor(ColorFilterUtilities.blendColor(getSelectedColor(), color, ratio))
    }
}

