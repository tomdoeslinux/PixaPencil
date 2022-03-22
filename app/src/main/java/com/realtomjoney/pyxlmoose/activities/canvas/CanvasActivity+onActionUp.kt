package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.PixelPerfectAlgorithm
import com.realtomjoney.pyxlmoose.database.BrushesDatabase

fun extendedOnActionUp() {
    if (currentTool == Tools.LINE_TOOL) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
        lineOrigin = null
        lineMode_hasLetGo = true
        rectangleMode_hasLetGo = true
    } else if (currentTool == Tools.RECTANGLE_TOOL || currentTool == Tools.OUTLINED_RECTANGLE_TOOL ||
        currentTool == Tools.SQUARE_TOOL || currentTool == Tools.OUTLINED_SQUARE_TOOL)  {
        rectangleToolOnActionUp()
    }
    else if(currentTool == Tools.POLYGON_TOOL) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
        )
    } else if (currentTool == Tools.CIRCLE_TOOL || currentTool == Tools.OUTLINED_CIRCLE_TOOL) {
        circleToolOnActionUp()
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)

        if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode
            && (currentTool == Tools.PENCIL_TOOL)
            && (outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush == null || outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush == BrushesDatabase.toList().first())) {
            val pixelPerfectAlgorithmInstance = PixelPerfectAlgorithm(AlgorithmInfoParameter.pass(
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                getSelectedColor()
            ))
            pixelPerfectAlgorithmInstance.compute()
        }

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null

        outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX = null
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY = null
    }
}