package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.doAddLast

fun CanvasActivity.ellipseToolOnActionUp() {
    if (
        viewModel.currentTool == Tool.EllipseTool &&
        viewModel.currentTool.outlined == false &&
        coordinates != null &&
        shapeOrigin != null) {

        if (shapeOrigin!!.x > coordinates!!.x) {
            filledEllipseAlgorithm.compute(coordinates!!, shapeOrigin!!)
        } else {
            filledEllipseAlgorithm.compute(shapeOrigin!!, coordinates!!)
        }
    }

    shapeOrigin = null
    firstShapeDrawn = false
    coordinates = null
    viewModel.undoStack.doAddLast(viewModel.currentBitmapAction!!)
}