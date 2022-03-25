package com.therealbluepandabear.pyxlmoose.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pyxlmoose.activities.canvas.currentTool
import com.therealbluepandabear.pyxlmoose.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.models.Coordinates
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun PixelGridView.extendedCoordinatesInCanvasBounds(coordinates: Coordinates): Boolean {
    return if (currentTool != Tools.DitherTool) {
            (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight)
    } else {
            (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight &&
                    outerCanvasInstance.transparentBackgroundView.transparentBackgroundViewBitmap.getPixel(coordinates.x, coordinates.y) == Color.parseColor(StringConstants.PixelGridViewCheckerboardColor))
    }
}
