package com.therealbluepandabear.pixapencil.activities.canvas.onactionup.root

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.undo
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.circleToolOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.ellipseToolOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.lineToolOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.rectangleToolOnActionUp
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.resetPreviousCoordinates() {
    binding.activityCanvasPixelGridView.prevX = null
    binding.activityCanvasPixelGridView.prevY = null
}

fun CanvasActivity.extendedOnActionUp() {
    if (viewModel.currentTool == Tool.ShadingTool) {
        binding.activityCanvasPixelGridView.shadingMap.clear()
    }

    when {
        viewModel.currentTool == Tool.LineTool -> {
            lineToolOnActionUp()
        }

        viewModel.currentTool.toolFamily == ToolFamily.Rectangle -> {
            rectangleToolOnActionUp()
        }

        viewModel.currentTool == Tool.PolygonTool -> {
            viewModel.undoStack.add(
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
            viewModel.undoStack.add(viewModel.currentBitmapAction!!)

            primaryAlgorithmInfoParameter.color = getSelectedColor()

            resetPreviousCoordinates()
        }

        viewModel.currentTool == Tool.ColorPickerTool -> {

        }

        else -> {
            val isPxPerfect = (binding.activityCanvasPixelGridView.pixelPerfectMode && viewModel.currentTool == Tool.PencilTool && (viewModel.currentBrush == BrushesDatabase.toList().first()))

            viewModel.undoStack.add(viewModel.currentBitmapAction!!)
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
                    canvasCommandsHelperInstance.overrideSetPixel(
                        Coordinates(
                            value.coordinates.x,
                            value.coordinates.y,
                        ),
                        getSelectedColor()
                    )
                }

                viewModel.undoStack.add(BitmapAction(data))
            }
        }
    }

    judgeUndoRedoStacks()
    viewModel.currentBitmapAction = null
}