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


