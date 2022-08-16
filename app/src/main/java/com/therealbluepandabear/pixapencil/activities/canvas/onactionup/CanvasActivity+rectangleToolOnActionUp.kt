package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.extensions.doAddLast

fun CanvasActivity.rectangleToolOnActionUp() {
    if (
        viewModel.currentTool.toolFamily == ToolFamily.Rectangle &&
        viewModel.currentTool.outlined == false &&
        coordinates != null &&
        shapeOrigin != null) {
        filledRectangleAlgorithm.compute(shapeOrigin!!, coordinates!!)
    }

    shapeOrigin = null
    firstShapeDrawn = false
    coordinates = null
    viewModel.undoStack.doAddLast(viewModel.currentBitmapAction!!)
}