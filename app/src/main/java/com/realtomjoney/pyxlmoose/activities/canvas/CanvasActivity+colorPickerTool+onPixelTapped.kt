package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.XYPosition

fun CanvasActivity.colorPickerToolOnPixelTapped(coordinatesTapped: XYPosition) {
    val color = canvasInstance.myCanvasViewInstance.extraBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)

    if (color == Color.TRANSPARENT) setPixelColor(Color.WHITE)
    else setPixelColor(color)
}