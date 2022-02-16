package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun PixelGridView.extendedOverrideSetPixel(x: Int, y: Int, color: Int) {
    val coordinates = Coordinates(x, y)
    if (coordinatesInCanvasBounds(coordinates)) {
        pixelGridViewBitmap.setPixel(coordinates.x, coordinates.y, color)

        if (currentBrush == null) {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, pixelGridViewBitmap.getPixel(coordinates.x, coordinates.y)))
        } else {
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                if (coordinatesInCanvasBounds(xyPosition_2)) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(xyPosition_2, pixelGridViewBitmap.getPixel(xyPosition_2.x, xyPosition_2.y)))
                    pixelGridViewBitmap.setPixel(xyPosition_2.x, xyPosition_2.y, color)
                }
            }
        }
    }
}