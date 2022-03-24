package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

var savedToActionData = true

private fun PixelGridView.setPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true) {
    savedToActionData = false

    if (saveToBitmapAction) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(
            BitmapActionData(
                coordinates,
                pixelGridViewBitmap.getPixel(coordinates.x, coordinates.y)
            )
        )
    }

    savedToActionData = true

    pixelGridViewBitmap.setPixel(coordinates.x, coordinates.y, color)
}

fun PixelGridView.extendedOverrideSetPixel(
    x: Int,
    y: Int,
    color: Int,
    ignoreBrush: Boolean = false,
    saveToBitmapAction: Boolean = true,
) {
    val coordinates = Coordinates(x, y)

    if (coordinatesInCanvasBounds(coordinates)) {
        setPixelAndSaveToBitmapAction(coordinates, color)
        if (currentBrush != null && !ignoreBrush) {
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                if (coordinatesInCanvasBounds(xyPosition_2)) {
                    setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                }
            }
        }
    }
}