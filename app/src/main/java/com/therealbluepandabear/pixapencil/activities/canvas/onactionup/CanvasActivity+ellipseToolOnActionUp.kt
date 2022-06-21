package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.coordinates
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.first
import com.therealbluepandabear.pixapencil.algorithms.EllipseAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool

fun CanvasActivity.ellipseToolOnActionUp() {
    if (viewModel.currentTool == Tool.EllipseTool && viewModel.currentTool.outlined == false) {
        val circleAlgorithmInstance = EllipseAlgorithm(primaryAlgorithmInfoParameter, true)

        if (shapeOrigin != null && coordinates != null) {
            if (shapeOrigin!!.x > coordinates!!.x) {
                circleAlgorithmInstance.compute(coordinates!!, shapeOrigin!!)
            } else {
                circleAlgorithmInstance.compute(shapeOrigin!!, coordinates!!)
            }
        }
    }

    viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

    coordinates = null
    shapeOrigin = null
    shapeHasLetGo = false
    first = true
}