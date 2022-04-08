package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.SquarePreviewAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

var firstCircleDrawn = false

fun circleToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val squarePreviewAlgorithmInstance = SquarePreviewAlgorithm(primaryAlgorithmInfoParameter, null, true)
    val circleAlgorithmInstance = CircleAlgorithm(primaryAlgorithmInfoParameter)

    if (!circleMode_hasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!)
        first = false

        if (firstCircleDrawn) {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!.actionData.clear()
        }
    } else {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction = null
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