package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.circleOrigin
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.coordinates
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.first
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.squareAlgorithmInstance
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.enums.ToolFamily

fun CanvasActivity.circleToolOnActionUp() {
    if (viewModel.currentTool.toolFamily == ToolFamily.Ellipse && viewModel.currentTool.outlined == false) {
        val circleAlgorithmInstance = CircleAlgorithm(primaryAlgorithmInfoParameter, true)

        if (circleOrigin!!.x > coordinates!!.x) {
            circleAlgorithmInstance.compute(coordinates!!, circleOrigin!!)
        } else {
            circleAlgorithmInstance.compute(circleOrigin!!, coordinates!!)
        }
    }

    viewModel.bitmapActionData.add(viewModel.currentBitmapAction!!)

    coordinates = null
    circleOrigin = null
    squareAlgorithmInstance = null
    circleModeHasLetGo = false
    first = true
}