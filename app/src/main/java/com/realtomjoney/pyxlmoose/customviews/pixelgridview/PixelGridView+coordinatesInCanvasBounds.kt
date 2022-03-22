package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Color
import com.realtomjoney.pyxlmoose.activities.canvas.currentTool
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.enums.Tools
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun PixelGridView.extendedCoordinatesInCanvasBounds(coordinates: Coordinates): Boolean {
    return if (currentTool != Tools.DitherTool) {
            (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight)
    } else {
            (coordinates.x in 0 until canvasWidth && coordinates.y in 0 until canvasHeight &&
                    outerCanvasInstance.transparentBackgroundView.transparentBackgroundViewBitmap.getPixel(coordinates.x, coordinates.y) == Color.parseColor(StringConstants.PixelGridViewCheckerboardColor))
    }
}
