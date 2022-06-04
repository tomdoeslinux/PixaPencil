package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates

fun PixelGridView.extendedCoordinatesInCanvasBounds(coordinates: Coordinates, currentTool: Tool, ignoreDither: Boolean = false): Boolean {
    return if (currentTool == Tool.DitherTool && !ignoreDither) {
                    (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight &&
                    currentDitherBrush.algorithm.invoke(coordinates))
    } else {
        (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight)
    }
}
