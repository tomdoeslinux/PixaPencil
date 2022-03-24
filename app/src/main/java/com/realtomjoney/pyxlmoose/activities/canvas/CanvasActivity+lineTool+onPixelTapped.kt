package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.BinaryPreviewStateSwitcher

fun lineToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

    if (!lineMode_hasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
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