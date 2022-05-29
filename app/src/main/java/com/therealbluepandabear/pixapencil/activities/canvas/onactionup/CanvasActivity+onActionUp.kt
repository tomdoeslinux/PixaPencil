package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun resetPreviousCoordinates() {
    pixelGridViewInstance.prevX = null
    pixelGridViewInstance.prevY = null
}

fun CanvasActivity.extendedOnActionUp() {
    if (currentTool == Tool.ShadingTool) {
        pixelGridViewInstance.shadingMap.clear()
    }

    when {
        currentTool == Tool.LineTool -> {
            lineToolOnActionUp()
        }

        currentTool.toolFamily == ToolFamily.Rectangle -> {
            rectangleToolOnActionUp()
        }

        currentTool == Tool.PolygonTool -> {
            viewModel.bitmapActionData.add(
                viewModel.currentBitmapAction!!
            )
        }

        currentTool.toolFamily == ToolFamily.Circle -> {
            circleToolOnActionUp()
        }

        currentTool == Tool.EraseTool -> {
            viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

            primaryAlgorithmInfoParameter.color = getSelectedColor()

            resetPreviousCoordinates()
        }

        else -> {
            viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)
            resetPreviousCoordinates()
        }
    }

    judgeUndoRedoStacks()
    viewModel.currentBitmapAction = null
}