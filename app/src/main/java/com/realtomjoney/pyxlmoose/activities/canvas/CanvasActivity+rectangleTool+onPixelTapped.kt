package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.RectanglePreviewAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.SquarePreviewAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates

var coordinates: Coordinates? = null
var rectangleAlgorithmInstance: RectanglePreviewAlgorithm? = null
var squareAlgorithmInstance: SquarePreviewAlgorithm? = null

fun rectangleToolOnPixelTapped(coordinatesTapped: Coordinates, hasBorder: Boolean) {

    if (currentTool == Tools.RECTANGLE_TOOL || currentTool == Tools.OUTLINED_RECTANGLE_TOOL) {
        rectangleAlgorithmInstance = if (!hasBorder) {
            RectanglePreviewAlgorithm(
                AlgorithmInfoParameter.pass(
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                    getSelectedColor()
                )
            )
        } else {
            RectanglePreviewAlgorithm(
                AlgorithmInfoParameter.pass(
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                    (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color
                )
            )
        }
    } else {
        squareAlgorithmInstance = if (!hasBorder) {
            SquarePreviewAlgorithm(
                AlgorithmInfoParameter.pass(
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                    getSelectedColor()
                )
            )
        } else {
            SquarePreviewAlgorithm(
                AlgorithmInfoParameter.pass(
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                    (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color
                )
            )
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