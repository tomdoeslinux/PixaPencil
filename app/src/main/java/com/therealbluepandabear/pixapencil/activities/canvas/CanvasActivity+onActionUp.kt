package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.algorithms.PixelPerfectAlgorithm
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun extendedOnActionUp() {
    if (currentTool == Tools.ShadingTool) {
        pixelGridViewInstance.shadingMap.clear()
    }

    when {
        currentTool == Tools.LineTool -> {
            lineToolOnActionUp()
        }

        currentTool.toolFamily == ToolFamily.Rectangle -> {
            rectangleToolOnActionUp()
        }

        currentTool == Tools.PolygonTool -> {
            pixelGridViewInstance.bitmapActionData.add(
               pixelGridViewInstance.currentBitmapAction!!
            )
        }

        currentTool.toolFamily == ToolFamily.Circle -> {
            circleToolOnActionUp()
        }

        currentTool == Tools.EraseTool -> {
            pixelGridViewInstance.bitmapActionData.add(pixelGridViewInstance.currentBitmapAction!!)

            primaryAlgorithmInfoParameter.color = getSelectedColor()

            pixelGridViewInstance.prevX = null
            pixelGridViewInstance.prevY = null
        }

        else -> {
            pixelGridViewInstance.bitmapActionData.add(pixelGridViewInstance.currentBitmapAction!!)

            if (pixelGridViewInstance.pixelPerfectMode
                && (currentTool == Tools.PencilTool)
                && (pixelGridViewInstance.currentBrush == null || pixelGridViewInstance.currentBrush == BrushesDatabase.toList().first())) {
                val pixelPerfectAlgorithmInstance = PixelPerfectAlgorithm(primaryAlgorithmInfoParameter)
                pixelPerfectAlgorithmInstance.compute()
            }

            pixelGridViewInstance.prevX = null
            pixelGridViewInstance.prevY = null
        }
    }

    if (pixelGridViewInstance.bitmapActionData.isNotEmpty() && currentTool.draws) {
        menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
    }

    if (pixelGridViewInstance.undoStack.isEmpty()) {
        menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).disable()
    }

    pixelGridViewInstance.currentBitmapAction = null
}