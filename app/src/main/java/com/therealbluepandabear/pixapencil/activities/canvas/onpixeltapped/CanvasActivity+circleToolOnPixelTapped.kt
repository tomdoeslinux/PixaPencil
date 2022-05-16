package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.SquarePreviewAlgorithm
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

var firstCircleDrawn = false

fun CanvasActivity.circleToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val squarePreviewAlgorithmInstance = SquarePreviewAlgorithm(primaryAlgorithmInfoParameter, null, true)
    val circleAlgorithmInstance = CircleAlgorithm(primaryAlgorithmInfoParameter)

    if (!circleMode_hasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(viewModel.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(viewModel.currentBitmapAction!!)
        first = false

        if (firstCircleDrawn) {
            viewModel.currentBitmapAction!!.actionData.clear()
        }
    } else {
        viewModel.currentBitmapAction = null
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