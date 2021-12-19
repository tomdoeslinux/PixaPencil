package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.view.View
import androidx.core.graphics.ColorUtils
import com.realtomjoney.pyxlmoose.utility.ColorFilters

private fun CanvasActivity.filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) setPixelColor(ColorFilters.blendColor(getSelectedColor(), color, ratio))
    else setPixelColor(ColorFilters.blendColor(getSelectedColor(), color, ratio))
}

fun CanvasActivity.darkenSelectedColor() = filterSelectedColor(Color.BLACK, 0.2f)
fun CanvasActivity.lightenSelectedColor() = filterSelectedColor(Color.WHITE, 0.2f)

fun applyCanvasFilter(lambda: (Int) -> Int) {
    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) pixel.pixelColor = lambda(pixel.pixelColor ?: Color.WHITE)

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
}

