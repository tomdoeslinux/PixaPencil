package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color

fun CanvasActivity.extendedOnFilterSelected(filterType: String) {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())

    when (filterType) {
        "COLOR_FILTER" -> applyColorFilterToCanvas(getSelectedColor())
        "DARKEN_FILTER" -> darkenCanvas()
        "LIGHTEN_FILTER" -> lightenCanvas()
        "INVERT_FILTER" -> {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = flipBits(pixel.pixelColor ?: Color.WHITE)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }
    }
}

fun flipBits(n: Int): Int {
    return n xor 0x00ffffff // TODO - Understand how this works a bit better
}