package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.RectangleAlgorithm
import com.realtomjoney.pyxlmoose.enums.Tools

fun rectangleToolOnActionUp() {
    if (coordinates != null && rectangleOrigin != null) {
        val rectAlg: RectangleAlgorithm =
            if (currentTool == Tools.OutlinedRectangleTool || currentTool == Tools.OutlinedSquareTool)
                RectangleAlgorithm(
                    AlgorithmInfoParameter.pass(
                        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                        (binding.activityCanvasColorSecondaryView.background as ColorDrawable).color
                    ),
                    (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color
                )
            else
                RectangleAlgorithm(
                    AlgorithmInfoParameter.pass(
                        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                        getSelectedColor()
                    )
                )

        rectAlg.compute(rectangleOrigin!!, coordinates!!)
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
    coordinates = null
    rectangleOrigin = null
    rectangleMode_hasLetGo = true
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
}