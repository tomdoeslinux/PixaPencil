package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun PixelGridView.extendedOverrideSetPixel(x: Int, y: Int, color: Int) {
    val xyPosition = Coordinates(x, y)

    if (currentBrush == null) {
        if (xyPosition.x in 0 until spanCount && xyPosition.y in 0 until spanCount) {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(
                BitmapActionData(
                    xyPosition,
                    pixelGridViewBitmap.getPixel(xyPosition.x, xyPosition.y)
                )
            )
            pixelGridViewBitmap.setPixel(xyPosition.x, xyPosition.y, color)
        }
    } else {
        if (xyPosition.x in 0 until spanCount && xyPosition.y in 0 until spanCount) {
            pixelGridViewBitmap.setPixel(xyPosition.x, xyPosition.y, color)
        }
        for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(xyPosition)) {
            if (xyPosition_2.x in 0 until spanCount && xyPosition_2.y in 0 until spanCount) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(
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