package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.saved
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates

fun PixelGridView.extendedApplyBitmapFilter(filterLambda: (Int) -> Int) {
    saved = false
    currentBitmapAction = BitmapAction(mutableListOf())

    for (i_1 in 0 until pixelGridViewBitmap.width) {
        for (i_2 in 0 until pixelGridViewBitmap.height) {
            val coordinates = Coordinates(i_1, i_2)
            val colorAtCoordinates = pixelGridViewBitmap.getPixel(coordinates)

            if (colorAtCoordinates != Color.TRANSPARENT) {
                val color = filterLambda(colorAtCoordinates)

                overrideSetPixel(coordinates, color, true)
            }
        }
    }

    bitmapActionData.add(currentBitmapAction!!)
    currentBitmapAction = null

    invalidate()
}