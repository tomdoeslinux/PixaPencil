package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.CircleAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.SquarePreviewAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates

fun circleToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val algP =  AlgorithmInfoParameter.pass(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
        getSelectedColor()
    )
    val squarePreviewAlgorithmInstance = SquarePreviewAlgorithm(
        algP, null, true
    )

    val circleAlgorithmInstance = CircleAlgorithm(
        algP
    )

    if (!circleMode_hasLetGo) {
        if (!first) outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
        first = false
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
    }
}