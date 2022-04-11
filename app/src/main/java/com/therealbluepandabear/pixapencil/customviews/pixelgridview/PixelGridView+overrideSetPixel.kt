package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates

var savedToActionData = true

private fun PixelGridView.setPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true) {
    savedToActionData = false

    if (saveToBitmapAction) {
        undoStack.clear()
        pixelGridViewInstance.currentBitmapAction!!.actionData.add(
            BitmapActionData(
                coordinates,
                pixelGridViewBitmap.getPixel(coordinates.x, coordinates.y),
                color
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