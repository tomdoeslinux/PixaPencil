package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.ToolFamily

fun CanvasActivity.circleToolOnActionUp() {
    if (
        viewModel.currentTool.toolFamily == ToolFamily.Ellipse &&
        viewModel.currentTool.outlined == false &&
        coordinates != null &&
        shapeOrigin != null) {

        if (shapeOrigin!!.x > coordinates!!.x) {
            filledCircleAlgorithm.compute(coordinates!!, shapeOrigin!!)
        } else {
            filledCircleAlgorithm.compute(shapeOrigin!!, coordinates!!)
        }
    }

    shapeOrigin = null
    firstShapeDrawn = false
    coordinates = null
    viewModel.undoStack.add(viewModel.currentBitmapAction!!)
}