package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.RectanglePreviewAlgorithm
import com.therealbluepandabear.pyxlmoose.algorithms.SquarePreviewAlgorithm
import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.models.Coordinates
import com.therealbluepandabear.pyxlmoose.utility.BinaryPreviewStateSwitcher

var coordinates: Coordinates? = null
var rectangleAlgorithmInstance: RectanglePreviewAlgorithm? = null
var squareAlgorithmInstance: SquarePreviewAlgorithm? = null

var firstRectangleDrawn = false

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
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
        first = false

        if (firstRectangleDrawn) {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.clear()
        }
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
        firstRectangleDrawn = true
    }
}