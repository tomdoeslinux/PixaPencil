package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.algorithms.PixelPerfectAlgorithm
import com.therealbluepandabear.pyxlmoose.database.BrushesDatabase
import com.therealbluepandabear.pyxlmoose.enums.ToolFamily
import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.extensions.disable
import com.therealbluepandabear.pyxlmoose.extensions.enable

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

        currentTool == Tools.EraseTool -> {
            primaryAlgorithmInfoParameter.color = getSelectedColor()
        }

        else -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)

            if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode
                && (currentTool == Tools.PencilTool)
                && (outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush == null || outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush == BrushesDatabase.toList().first())) {
                val pixelPerfectAlgorithmInstance = PixelPerfectAlgorithm(primaryAlgorithmInfoParameter)
                pixelPerfectAlgorithmInstance.compute()
            }

            outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevX = null
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.prevY = null
        }
    }

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.isNotEmpty() && currentTool.draws) {
        menu.findItem(R.id.undo).enable()
    }

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.undoStack.isEmpty()) {
        menu.findItem(R.id.redo).disable()
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
}