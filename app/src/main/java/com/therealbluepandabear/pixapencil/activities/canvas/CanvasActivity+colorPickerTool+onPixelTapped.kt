package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)

    if (color == Color.TRANSPARENT) {
        setPixelColor(Color.WHITE)
    } else {
        setPixelColor(color)
    }
}