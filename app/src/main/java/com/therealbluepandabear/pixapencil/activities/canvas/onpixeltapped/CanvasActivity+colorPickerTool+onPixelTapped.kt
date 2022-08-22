package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.coordinatesInCanvasBounds
import com.therealbluepandabear.pixapencil.activities.canvas.setPixelColor
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (canvasCommandsHelperInstance.coordinatesInCanvasBounds(coordinatesTapped, Tool.ColorPickerTool)) {
        val color = binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(coordinatesTapped)
        setPixelColor(color)
    }
}