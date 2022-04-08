package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.RectangleAlgorithm
import com.therealbluepandabear.pixapencil.enums.ToolFamily

fun rectangleToolOnActionUp() {
    if (coordinates != null && rectangleOrigin != null) {
        val rectAlg: RectangleAlgorithm? =
            if (currentTool.toolFamily == ToolFamily.Rectangle && currentTool.outlined == false) {
                RectangleAlgorithm(primaryAlgorithmInfoParameter)
            } else {
                null
            }

        rectAlg?.compute(rectangleOrigin!!, coordinates!!)
    }

    outerCanvasInstance.canvasFragment.pixelGridViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!
    )

    coordinates = null
    rectangleOrigin = null
    rectangleMode_hasLetGo = false
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
    first = true
}