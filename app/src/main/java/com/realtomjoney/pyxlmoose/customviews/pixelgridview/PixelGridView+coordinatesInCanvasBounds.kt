package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Color
import com.realtomjoney.pyxlmoose.activities.canvas.Tools
import com.realtomjoney.pyxlmoose.activities.canvas.currentTool
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.Coordinates

fun PixelGridView.extendedCoordinatesInCanvasBounds(coordinates: Coordinates): Boolean {
    return if (currentTool != Tools.DITHER_TOOL)
        (coordinates.x in 0 until canvasSize && coordinates.y in 0 until canvasSize)
    else
        (coordinates.x in 0 until canvasSize && coordinates.y in 0 until canvasSize && outerCanvasInstance.transparentBackgroundView.transparentBackgroundViewBitmap.getPixel(coordinates.x, coordinates.y) == Color.LTGRAY)
}