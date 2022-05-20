package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.currentTool
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun PixelGridView.extendedCoordinatesInCanvasBounds(coordinates: Coordinates, ignoreDither: Boolean = false): Boolean {
    return if (currentTool == Tool.DitherTool && !ignoreDither) {
            (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight &&
                    outerCanvasInstance.transparentBackgroundView.transparentBackgroundViewBitmap.getPixel(coordinates.x, coordinates.y) == Color.parseColor(StringConstants.Colors.PixelGridViewCheckerboardColor))
    } else {
        (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight)
    }
}
