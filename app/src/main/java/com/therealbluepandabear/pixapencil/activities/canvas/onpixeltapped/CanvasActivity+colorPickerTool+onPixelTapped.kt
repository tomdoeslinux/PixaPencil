package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.setPixelColor
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped)

    setPixelColor(color)
}