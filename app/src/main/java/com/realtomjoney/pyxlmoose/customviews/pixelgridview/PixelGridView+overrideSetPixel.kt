package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

private fun PixelGridView.setPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int) {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(
        BitmapActionData(
            coordinates,
            pixelGridViewBitmap.getPixel(coordinates.x, coordinates.y)
        )
    )
    pixelGridViewBitmap.setPixel(coordinates.x, coordinates.y, color)
}

fun PixelGridView.extendedOverrideSetPixel(
    x: Int,
    y: Int,
    color: Int,
    ignoreBrush: Boolean = false
) {
    val coordinates = Coordinates(x, y)

    if (coordinatesInCanvasBounds(coordinates)) {
        setPixelAndSaveToBitmapAction(coordinates, color)
        if (currentBrush != null && !ignoreBrush) {
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                if (coordinatesInCanvasBounds(xyPosition_2)) {
                    setPixelAndSaveToBitmapAction(xyPosition_2, color)
                }
            }
        }
    }
}