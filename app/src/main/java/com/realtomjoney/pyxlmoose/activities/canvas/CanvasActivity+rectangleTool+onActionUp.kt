package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.RectangleAlgorithm

fun rectangleToolOnActionUp() {
    if (coordinates != null && rectangleOrigin != null) {
        val rectAlg: RectangleAlgorithm =
            if (currentTool == Tools.OUTLINED_RECTANGLE_TOOL || currentTool == Tools.OUTLINED_SQUARE_TOOL)
                RectangleAlgorithm(
                    AlgorithmInfoParameter(
                        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
                        (binding.activityCanvasColorSecondaryView.background as ColorDrawable).color
                    ),
                    (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color
                )
            else
                RectangleAlgorithm(
                    AlgorithmInfoParameter(
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