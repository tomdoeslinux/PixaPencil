package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.PixelPerfectAlgorithm
import com.realtomjoney.pyxlmoose.database.BrushesDatabase
import com.realtomjoney.pyxlmoose.enums.ToolFamily
import com.realtomjoney.pyxlmoose.enums.Tools

fun extendedOnActionUp() {
    when {
        currentTool == Tools.LineTool -> {
            lineToolOnActionUp()
        }

        currentTool.toolFamily == ToolFamily.Rectangle -> {
            rectangleToolOnActionUp()
        }

        currentTool == Tools.PolygonTool -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
            )
        }

        currentTool.toolFamily == ToolFamily.Circle -> {
            circleToolOnActionUp()
        }

        else -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)

            if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode
                && (currentTool == Tools.PencilTool)
                && (outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush == null || outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush == BrushesDatabase.toList().first())) {
                val pixelPerfectAlgorithmInstance = PixelPerfectAlgorithm(primaryAlgorithmInfoParameter)
                pixelPerfectAlgorithmInstance.compute()
            }

            outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null

            outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX = null
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY = null
        }
    }
}