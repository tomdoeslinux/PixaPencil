package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Color
import com.realtomjoney.pyxlmoose.activities.canvas.canvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

fun PixelGridView.extendedApplyBitmapFilter(lambda: (Int) -> Int) {
    currentBitmapAction = BitmapAction(mutableListOf(), true)

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            if (pixelGridViewBitmap.getPixel(i_1, i_2) != Color.TRANSPARENT) {
                val color = lambda(pixelGridViewBitmap.getPixel(i_1, i_2))

                currentBitmapAction!!.actionData.add(
                    BitmapActionData(
                    Coordinates(i_1, i_2),
                    pixelGridViewBitmap.getPixel(i_1, i_2),
                )
                )

                pixelGridViewBitmap.setPixel(i_1, i_2, color)
            }
        }
    }

    canvasInstance.myCanvasViewInstance.bitmapActionData.add(canvasInstance.myCanvasViewInstance.currentBitmapAction!!)
    canvasInstance.myCanvasViewInstance.currentBitmapAction = null

    invalidate()
}