package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.activities.canvas.extendedUndo
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.BitmapActionData


class PixelPerfectAlgorithm(private val algorithmInfoParameter: AlgorithmInfoParameter) {
    fun compute() {
        var distinct = algorithmInfoParameter.currentBitmapAction.actionData.distinctBy { it.coordinates }
        val data = mutableListOf<BitmapActionData>()

        var index = 0

        while (index < distinct.size) {
            if (index > 0 && index + 1 < distinct.size
                && (distinct[index - 1].coordinates.x == distinct[index].coordinates.x || distinct[index - 1].coordinates.y == distinct[index].coordinates.y)
                && (distinct[index + 1].coordinates.x == distinct[index].coordinates.x || distinct[index + 1].coordinates.y == distinct[index].coordinates.y)
                && distinct[index - 1].coordinates.x != distinct[index + 1].coordinates.x
                && distinct[index - 1].coordinates.y != distinct[index + 1].coordinates.y
            ) {
                index += 1
            }

            data.add(distinct[index])

            index += 1
        }

        extendedUndo()

        for (value in data) {
            distinct = distinct.filter { it == value }
        }

        pixelGridViewInstance.currentBitmapAction = BitmapAction(mutableListOf())

        for (value in data) {
            pixelGridViewInstance.overrideSetPixel(value.coordinates.x, value.coordinates.y, algorithmInfoParameter.color)
        }

        pixelGridViewInstance.bitmapActionData.add(pixelGridViewInstance.currentBitmapAction!!)
    }
}