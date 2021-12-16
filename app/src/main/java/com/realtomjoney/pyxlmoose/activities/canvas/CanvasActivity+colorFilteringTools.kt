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

