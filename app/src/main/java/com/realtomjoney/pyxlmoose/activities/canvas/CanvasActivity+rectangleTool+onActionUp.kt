package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.RectangleAlgorithm
import com.realtomjoney.pyxlmoose.enums.ToolFamily

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

    coordinates = null
    rectangleOrigin = null
    rectangleMode_hasLetGo = true
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
}