package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*

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
        "INVERT_RED_FILTER" ->  {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = flipRed(pixel.pixelColor ?: Color.WHITE)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }
        "INVERT_GREEN_FILTER" -> {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = flipGreen(pixel.pixelColor ?: Color.WHITE)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }
        "INVERT_BLUE_FILTER" -> {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = flipBlue(pixel.pixelColor ?: Color.WHITE)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }
        "GRAYSCALE_FILTER" -> {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = performCustomGrayscaleAlgorithm(pixel.pixelColor ?: Color.WHITE)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }

        "GRAYSCALE_FILTER_TWO" -> {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = performCustomGrayscaleAlgorithm(pixel.pixelColor ?: Color.WHITE, 0)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }

        "GRAYSCALE_FILTER_THREE" -> {
            val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

            for (pixel in dataAsPixelList) pixel.pixelColor = performCustomGrayscaleAlgorithm(pixel.pixelColor ?: Color.WHITE, 1)

            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
        }
    }
}

// Solution by Andrew Gallasch (https://stackoverflow.com/users/3841420/andrew-gallasch) on StackOverFlow (https://stackoverflow.com/questions/18141976/how-to-invert-an-rgb-color-in-integer-form)
fun flipBits(n: Int) = n xor 0x00ffffff // TODO - Understand how this works a bit better

fun flipRed(n: Int) = n xor 0x00ff0000 // TODO - Understand how this works a bit better
fun flipGreen(n: Int) = n xor 0x0000ff00 // TODO - Understand how this works a bit better
fun flipBlue(n: Int) = n xor 0x0000000ff // TODO - Understand how this works a bit better

fun performCustomGrayscaleAlgorithm(n: Int, strength: Int = 10): Int {
    // TODO - research whether this a good way to convert a color to grayscale?

    var color = n

    for (i in 0..strength) {
        val r = Color.valueOf(color).red()
        val g = Color.valueOf(color).green()
        val b = Color.valueOf(color).blue()

        color = (Color.rgb((r + g + b) / 3.toFloat(), r, r))
    }

    return color
}

