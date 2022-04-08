package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.models.Coordinates

fun colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)

    if (color == Color.TRANSPARENT) {
        setPixelColor(Color.WHITE)
    } else {
        setPixelColor(color)
    }
}