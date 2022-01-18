package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.Coordinates

fun CanvasActivity.colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)

    if (color == Color.TRANSPARENT) setPixelColor(Color.WHITE)
    else setPixelColor(color)
}