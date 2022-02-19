package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun PixelGridView.extendedOverrideSetPixel(
    x: Int,
    y: Int,
    color: Int,
    ignoreBrush: Boolean = false
) {
    val coordinates = Coordinates(x, y)
    if (coordinatesInCanvasBounds(coordinates)) {
        currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(x, y), pixelGridViewBitmap.getPixel(x, y)))
        pixelGridViewBitmap.setPixel(coordinates.x, coordinates.y, color)

        if (currentBrush != null && !ignoreBrush) {
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                if (coordinatesInCanvasBounds(xyPosition_2)) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(xyPosition_2, pixelGridViewBitmap.getPixel(xyPosition_2.x, xyPosition_2.y)))
                    pixelGridViewBitmap.setPixel(xyPosition_2.x, xyPosition_2.y, color)
                }
            }
        }
    }
}