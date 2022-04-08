package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.RectanglePreviewAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.SquarePreviewAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

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
            BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!)
        first = false

        if (firstRectangleDrawn) {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!.actionData.clear()
        }
    } else {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction = null
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