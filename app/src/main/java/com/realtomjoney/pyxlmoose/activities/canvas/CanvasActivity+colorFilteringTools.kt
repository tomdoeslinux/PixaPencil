package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import androidx.core.graphics.ColorUtils

fun darkenCanvas(ratio: Float = 0.2f) = applyColorFilterToCanvas(Color.BLACK, ratio)

fun lightenCanvas(ratio: Float = 0.2f) = applyColorFilterToCanvas(Color.WHITE, ratio)

fun applyColorFilterToCanvas(color: Int, ratio: Float = 0.2f) {
    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) pixel.pixelColor = ColorUtils.blendARGB(pixel.pixelColor ?: Color.WHITE, color, ratio)

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
}

private fun CanvasActivity.filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) setPixelColor(ColorUtils.blendARGB(getSelectedColor(), color, ratio))
    else setPixelColor(ColorUtils.blendARGB(getSelectedColor(), color, ratio))
}

fun CanvasActivity.darkenSelectedColor() = filterSelectedColor(Color.BLACK, 0.2f)
fun CanvasActivity.lightenSelectedColor() = filterSelectedColor(Color.WHITE, 0.2f)

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

