package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pyxlmoose.models.Coordinates

fun colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)

    if (color == Color.TRANSPARENT) {
        setPixelColor(Color.WHITE)
    } else {
        setPixelColor(color)
    }
}