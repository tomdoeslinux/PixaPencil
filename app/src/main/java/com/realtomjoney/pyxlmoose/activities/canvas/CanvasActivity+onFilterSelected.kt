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

// Solution by Andrew Gallasch (https://stackoverflow.com/users/3841420/andrew-gallasch) on StackOverFlow (https://stackoverflow.com/questions/18141976/how-to-invert-an-rgb-color-in-integer-form)
fun flipBits(n: Int) = n xor 0x00ffffff // TODO - Understand how this works a bit better
