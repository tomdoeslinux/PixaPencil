package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.CircleAlgorithm
import com.realtomjoney.pyxlmoose.enums.Tools

fun circleToolOnActionUp() {
    if (currentTool == Tools.CircleTool) {
        val circleAlgorithmInstance = CircleAlgorithm(primaryAlgorithmInfoParameter, true)

        if (circleOrigin!!.x > coordinates!!.x) {
            circleAlgorithmInstance.compute(coordinates!!, circleOrigin!!)
        } else {
            circleAlgorithmInstance.compute(circleOrigin!!, coordinates!!)
        }
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
    coordinates = null
    circleOrigin = null
    circleMode_hasLetGo = true
}