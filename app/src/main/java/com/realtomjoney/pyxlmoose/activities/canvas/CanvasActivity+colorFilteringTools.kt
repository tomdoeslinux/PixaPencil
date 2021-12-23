package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.utility.ColorFilters

fun CanvasActivity.filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) setPixelColor(ColorFilters.blendColor(getSelectedColor(), color, ratio))
    else setPixelColor(ColorFilters.blendColor(getSelectedColor(), color, ratio))
}

fun applyCanvasFilter(lambda: (Int) -> Int) {
    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) pixel.pixelColor = lambda(pixel.pixelColor ?: Color.WHITE)

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
    canvasFragmentInstance.myCanvasViewInstance.invalidate()
}

