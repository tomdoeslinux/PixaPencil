package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.CircleAlgorithm
import com.therealbluepandabear.pyxlmoose.enums.ToolFamily

fun circleToolOnActionUp() {
    if (currentTool.toolFamily == ToolFamily.Circle && currentTool.outlined == false) {
        val circleAlgorithmInstance = CircleAlgorithm(primaryAlgorithmInfoParameter, true)

        if (circleOrigin!!.x > coordinates!!.x) {
            circleAlgorithmInstance.compute(coordinates!!, circleOrigin!!)
        } else {
            circleAlgorithmInstance.compute(circleOrigin!!, coordinates!!)
        }
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!
    )

    coordinates = null
    circleOrigin = null
    squareAlgorithmInstance = null
    circleMode_hasLetGo = false
    first = true
}