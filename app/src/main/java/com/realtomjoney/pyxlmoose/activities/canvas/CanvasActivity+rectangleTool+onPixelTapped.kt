package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.RectanglePreviewAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.SquarePreviewAlgorithm
import com.realtomjoney.pyxlmoose.enums.Tools
import com.realtomjoney.pyxlmoose.models.Coordinates

var coordinates: Coordinates? = null
var rectangleAlgorithmInstance: RectanglePreviewAlgorithm? = null
var squareAlgorithmInstance: SquarePreviewAlgorithm? = null

fun rectangleToolOnPixelTapped(coordinatesTapped: Coordinates, hasBorder: Boolean) {

    if (currentTool == Tools.RectangleTool || currentTool == Tools.OutlinedRectangleTool) {
        rectangleAlgorithmInstance = if (!hasBorder) {
            RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter)
        } else {
            RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter)
        }
    } else {
        squareAlgorithmInstance = if (!hasBorder) {
            SquarePreviewAlgorithm(primaryAlgorithmInfoParameter)
        } else {
            SquarePreviewAlgorithm(primaryAlgorithmInfoParameter)
        }
    }

    if (!rectangleMode_hasLetGo) {
        if (!first)  outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
        first = false
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
        rectangleMode_hasLetGo = false
        first = true
    }

    if (rectangleOrigin == null) {
        rectangleOrigin = coordinatesTapped
    } else {
        if (rectangleAlgorithmInstance != null) {
            rectangleAlgorithmInstance!!.compute(rectangleOrigin!!, coordinatesTapped)
            coordinates = coordinatesTapped
        } else {
            squareAlgorithmInstance!!.compute(rectangleOrigin!!, coordinatesTapped)
        }
    }
}