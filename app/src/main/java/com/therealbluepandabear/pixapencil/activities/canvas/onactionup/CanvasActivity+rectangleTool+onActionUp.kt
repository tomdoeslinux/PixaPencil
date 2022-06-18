package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.*
import com.therealbluepandabear.pixapencil.algorithms.RectangleAlgorithm
import com.therealbluepandabear.pixapencil.enums.ToolFamily

fun CanvasActivity.rectangleToolOnActionUp() {
    if (coordinates != null && shapeOrigin != null) {
        val rectAlg: RectangleAlgorithm? =
            if (viewModel.currentTool.toolFamily == ToolFamily.Rectangle && viewModel.currentTool.outlined == false) {
                RectangleAlgorithm(primaryAlgorithmInfoParameter)
            } else {
                null
            }

        rectAlg?.compute(shapeOrigin!!, coordinates!!)
    }

    viewModel.bitmapActionData.add(
        viewModel.currentBitmapAction!!
    )

    coordinates = null
    shapeOrigin = null
    rectangleModeHasLetGo = false
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
    first = true
}