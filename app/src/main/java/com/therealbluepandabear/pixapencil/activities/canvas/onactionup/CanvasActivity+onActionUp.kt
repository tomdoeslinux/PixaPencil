package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.undo
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.BitmapActionData

fun resetPreviousCoordinates() {
    pixelGridViewInstance.prevX = null
    pixelGridViewInstance.prevY = null
}

fun CanvasActivity.extendedOnActionUp() {
    if (viewModel.currentTool == Tool.ShadingTool) {
        pixelGridViewInstance.shadingMap.clear()
    }

    when {
        viewModel.currentTool == Tool.LineTool -> {
            lineToolOnActionUp()
        }

        viewModel.currentTool.toolFamily == ToolFamily.Rectangle -> {
            rectangleToolOnActionUp()
        }

        viewModel.currentTool == Tool.PolygonTool -> {
            viewModel.bitmapActionData.add(
                viewModel.currentBitmapAction!!
            )
        }

        viewModel.currentTool.toolFamily == ToolFamily.Ellipse && (viewModel.currentTool == Tool.CircleTool || viewModel.currentTool == Tool.OutlinedCircleTool) -> {
            circleToolOnActionUp()
        }

        viewModel.currentTool.toolFamily == ToolFamily.Ellipse && (viewModel.currentTool == Tool.EllipseTool || viewModel.currentTool == Tool.OutlinedEllipseTool) -> {
            ellipseToolOnActionUp()
        }

        viewModel.currentTool == Tool.EraseTool -> {
            viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

            primaryAlgorithmInfoParameter.color = getSelectedColor()

            resetPreviousCoordinates()
        }

        viewModel.currentTool == Tool.ColorPickerTool -> {

        }

        else -> {
            val isPxPerfect = (pixelGridViewInstance.pixelPerfectMode && viewModel.currentTool == Tool.PencilTool && (viewModel.currentBrush == BrushesDatabase.toList().first()))

            viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)
            resetPreviousCoordinates()

            if (isPxPerfect) {
                var distinct = viewModel.currentBitmapAction!!.actionData.distinctBy { it.coordinates }
                val data = mutableListOf<BitmapActionData>()

                var c = 0

                while (c < distinct.size) {
                    if (c > 0 && c + 1 < distinct.size
                        && (distinct[c - 1].coordinates.x == distinct[c].coordinates.x || distinct[c - 1].coordinates.y == distinct[c].coordinates.y)
                        && (distinct[c + 1].coordinates.x == distinct[c].coordinates.x || distinct[c + 1].coordinates.y == distinct[c].coordinates.y)
                        && distinct[c - 1].coordinates.x != distinct[c + 1].coordinates.x
                        && distinct[c - 1].coordinates.y != distinct[c + 1].coordinates.y
                    ) {
                        c += 1
                    }

                    data.add(distinct[c])

                    c += 1
                }

                canvasCommandsHelperInstance.undo()

                for (value in data) {
                    distinct = distinct.filter { it == value }
                }

                for (value in data) {
                    pixelGridViewInstance.pixelGridViewBitmap.setPixel(
                        value.coordinates.x,
                        value.coordinates.y,
                        getSelectedColor()
                    )
                }

                viewModel.bitmapActionData.add(BitmapAction(data))
            }
        }
    }

    judgeUndoRedoStacks()
    viewModel.currentBitmapAction = null
}