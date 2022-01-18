package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.utility.ColorFilters

fun CanvasActivity.filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) setPixelColor(ColorFilters.blendColor(getSelectedColor(), color, ratio))
    else setPixelColor(ColorFilters.blendColor(getSelectedColor(), color, ratio))
}

