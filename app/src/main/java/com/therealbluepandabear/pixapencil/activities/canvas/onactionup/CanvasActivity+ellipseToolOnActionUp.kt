package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.*
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.EllipseAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.enums.ToolFamily

fun CanvasActivity.ellipseToolOnActionUp() {
    if (currentTool == Tool.EllipseTool && currentTool.outlined == false) {
        val circleAlgorithmInstance = EllipseAlgorithm(primaryAlgorithmInfoParameter, true)

        if (ellipseOrigin!!.x > coordinates!!.x) {
            circleAlgorithmInstance.compute(coordinates!!, ellipseOrigin!!)
        } else {
            circleAlgorithmInstance.compute(ellipseOrigin!!, coordinates!!)
        }
    }

    viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

    coordinates = null
    ellipseOrigin = null
    ellipseModeHasLetGo = false
    first = true
}