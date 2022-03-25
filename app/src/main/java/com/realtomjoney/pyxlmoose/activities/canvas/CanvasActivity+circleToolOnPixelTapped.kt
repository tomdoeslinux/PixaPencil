package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.CircleAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.SquarePreviewAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.BinaryPreviewStateSwitcher

var firstCircleDrawn = false

fun circleToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val squarePreviewAlgorithmInstance = SquarePreviewAlgorithm(primaryAlgorithmInfoParameter, null, true)
    val circleAlgorithmInstance = CircleAlgorithm(primaryAlgorithmInfoParameter)

    if (!circleMode_hasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
        first = false

        if (firstCircleDrawn) {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.clear()
        }
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
        circleMode_hasLetGo = false
        first = true
    }

    if (circleOrigin == null) {
        circleOrigin = coordinatesTapped
    } else {
        coordinates = coordinatesTapped

        squarePreviewAlgorithmInstance.compute(circleOrigin!!, coordinatesTapped)

        if (circleOrigin!!.x > coordinates!!.x) {
            circleAlgorithmInstance.compute(coordinates!!, circleOrigin!!)
        } else {
            circleAlgorithmInstance.compute(circleOrigin!!, coordinates!!)
        }

        firstCircleDrawn = true
    }
}