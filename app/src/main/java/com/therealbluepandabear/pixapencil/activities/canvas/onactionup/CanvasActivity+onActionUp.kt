package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.algorithms.PixelPerfectAlgorithm
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.extendedOnActionUp() {
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
            viewModel.bitmapActionData.add(
                viewModel.currentBitmapAction!!
            )
        }

        currentTool.toolFamily == ToolFamily.Circle -> {
            circleToolOnActionUp()
        }

        currentTool == Tools.EraseTool -> {
            viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

            primaryAlgorithmInfoParameter.color = getSelectedColor()

            pixelGridViewInstance.prevX = null
            pixelGridViewInstance.prevY = null
        }

        else -> {
            viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

            if (pixelGridViewInstance.pixelPerfectMode
                && (currentTool == Tools.PencilTool)
                && (pixelGridViewInstance.currentBrush == null || pixelGridViewInstance.currentBrush == BrushesDatabase.toList().first())) {
                val pixelPerfectAlgorithmInstance = PixelPerfectAlgorithm(
                    primaryAlgorithmInfoParameter
                )
                pixelPerfectAlgorithmInstance.compute()
            }

            pixelGridViewInstance.prevX = null
            pixelGridViewInstance.prevY = null
        }
    }

    judgeUndoRedoStacks()
    viewModel.currentBitmapAction = null
}