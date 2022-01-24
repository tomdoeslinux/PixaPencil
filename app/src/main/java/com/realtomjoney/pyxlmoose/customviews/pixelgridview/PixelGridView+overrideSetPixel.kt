package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.canvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun PixelGridView.extendedOverrideSetPixel(x: Int, y: Int, color: Int) {
    val xyPosition = Coordinates(x, y)

    if (currentBrush == null) {
        pixelGridViewBitmap.setPixel(xyPosition.x, xyPosition.y, color)
    } else {
        pixelGridViewBitmap.setPixel(xyPosition.x, xyPosition.y, color)
        for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(xyPosition)) {
            if (xyPosition_2.x in 0 until spanCount && xyPosition_2.y in 0 until spanCount) {
                canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(
                    BitmapActionData(
                    xyPosition_2,
                    pixelGridViewBitmap.getPixel(xyPosition_2.x, xyPosition_2.y)
                )
                )
                pixelGridViewBitmap.setPixel(xyPosition_2.x, xyPosition_2.y, color)
            }
        }
    }
}