package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.models.Coordinates

fun CanvasActivity.lineToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val lineAlgorithmInstance = LineAlgorithm(AlgorithmInfoParameter(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap, outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor()))

    if (!lineMode_hasLetGo) {
        if (!first) outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
        first = false
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
        lineMode_hasLetGo = false
        first = true
    }

    if (lineOrigin == null) {
        lineOrigin = coordinatesTapped
    } else {
        lineAlgorithmInstance.compute(lineOrigin!!, coordinatesTapped)
    }
}