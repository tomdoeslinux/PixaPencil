package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.*
import com.therealbluepandabear.pixapencil.algorithms.RectangleAlgorithm
import com.therealbluepandabear.pixapencil.enums.ToolFamily

fun CanvasActivity.rectangleToolOnActionUp() {
    if (coordinates != null && rectangleOrigin != null) {
        val rectAlg: RectangleAlgorithm? =
            if (viewModel.currentTool.toolFamily == ToolFamily.Rectangle && viewModel.currentTool.outlined == false) {
                RectangleAlgorithm(primaryAlgorithmInfoParameter)
            } else {
                null
            }

        rectAlg?.compute(rectangleOrigin!!, coordinates!!)
    }

    viewModel.bitmapActionData.add(
        viewModel.currentBitmapAction!!
    )

    coordinates = null
    rectangleOrigin = null
    rectangleModeHasLetGo = false
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
    first = true
}