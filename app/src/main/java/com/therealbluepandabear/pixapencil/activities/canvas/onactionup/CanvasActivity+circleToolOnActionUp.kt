package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.circleMode_hasLetGo
import com.therealbluepandabear.pixapencil.activities.canvas.currentTool
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.circleOrigin
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.coordinates
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.first
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.squareAlgorithmInstance
import com.therealbluepandabear.pixapencil.activities.canvas.primaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.circleToolOnActionUp() {
    if (currentTool.toolFamily == ToolFamily.Circle && currentTool.outlined == false) {
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
    circleMode_hasLetGo = false
    first = true
}