package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.RectangleAlgorithm

fun CanvasActivity.rectangleToolOnActionUp() {
    if (coordinates != null && rectangleOrigin != null) {
        val rectAlg: RectangleAlgorithm =
            if (currentTool == Tools.OUTLINED_RECTANGLE_TOOL)
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
    coordinates = null
    rectangleOrigin = null
    rectangleMode_hasLetGo = true
}