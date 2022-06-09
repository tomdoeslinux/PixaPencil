package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.algorithms.RectanglePreviewAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.SquarePreviewAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

var coordinates: Coordinates? = null
var rectangleAlgorithmInstance: RectanglePreviewAlgorithm? = null
var squareAlgorithmInstance: SquarePreviewAlgorithm? = null

var firstRectangleDrawn = false

fun CanvasActivity.rectangleToolOnPixelTapped(coordinatesTapped: Coordinates, hasBorder: Boolean) {

    if (viewModel.currentTool == Tool.RectangleTool || viewModel.currentTool == Tool.OutlinedRectangleTool) {
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

    if (!rectangleModeHasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(viewModel.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(viewModel.currentBitmapAction!!)
        first = false

        if (firstRectangleDrawn) {
            viewModel.currentBitmapAction!!.actionData.clear()
        }
    } else {
        viewModel.currentBitmapAction = null
        rectangleModeHasLetGo = false
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