package com.therealbluepandabear.pyxlmoose.algorithms

import com.therealbluepandabear.pyxlmoose.activities.canvas.extendedUndo
import com.therealbluepandabear.pyxlmoose.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pyxlmoose.models.BitmapAction
import com.therealbluepandabear.pyxlmoose.models.BitmapActionData


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

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())

        for (value in data) {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(value.coordinates.x, value.coordinates.y, algorithmInfoParameter.color)
        }

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add( outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
    }
}