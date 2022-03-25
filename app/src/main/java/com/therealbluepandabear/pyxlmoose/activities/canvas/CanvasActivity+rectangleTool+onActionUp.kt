package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.RectangleAlgorithm
import com.therealbluepandabear.pyxlmoose.enums.ToolFamily

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

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
    )

    coordinates = null
    rectangleOrigin = null
    rectangleMode_hasLetGo = false
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
    first = true
}