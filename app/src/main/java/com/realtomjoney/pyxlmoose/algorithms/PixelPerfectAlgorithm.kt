package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.activities.canvas.extendedUndo
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData

class PixelPerfectAlgorithm(private val algorithmInfoParameter: AlgorithmInfoParameter) {
    fun compute() {
        var distinct = algorithmInfoParameter.currentBitmapAction.actionData.distinctBy { it.xyPosition }
        val data = mutableListOf<BitmapActionData>()

        var index = 0

        while (index < distinct.size) {
            if (index > 0 && index + 1 < distinct.size
                && (distinct[index - 1].xyPosition.x == distinct[index].xyPosition.x || distinct[index - 1].xyPosition.y == distinct[index].xyPosition.y)
                && (distinct[index + 1].xyPosition.x == distinct[index].xyPosition.x || distinct[index + 1].xyPosition.y == distinct[index].xyPosition.y)
                && distinct[index - 1].xyPosition.x != distinct[index + 1].xyPosition.x
                && distinct[index - 1].xyPosition.y != distinct[index + 1].xyPosition.y
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
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(value.xyPosition.x, value.xyPosition.y, algorithmInfoParameter.color)
        }

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add( outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
    }
}