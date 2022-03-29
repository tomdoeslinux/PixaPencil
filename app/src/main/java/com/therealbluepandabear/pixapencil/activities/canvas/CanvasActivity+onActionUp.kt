package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.algorithms.PixelPerfectAlgorithm
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable

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
        menu.findItem(R.id.appMenu_undo).enable()
    }

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.undoStack.isEmpty()) {
        menu.findItem(R.id.appMenu_redo_item).disable()
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
}