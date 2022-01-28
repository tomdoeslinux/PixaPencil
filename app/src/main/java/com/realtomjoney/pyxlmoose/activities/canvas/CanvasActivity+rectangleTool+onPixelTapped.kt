package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.algorithms.shapes.RectangleAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.models.Coordinates

fun CanvasActivity.rectangleToolOnPixelTapped(coordinatesTapped: Coordinates, hasBorder: Boolean) {

    val rectangleAlgorithmInstance: RectangleAlgorithm = if (!hasBorder) {
        RectangleAlgorithm(AlgorithmInfoParameter(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor()))
    } else {
        RectangleAlgorithm(AlgorithmInfoParameter(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, (binding.activityCanvasColorSecondaryView.background as ColorDrawable).color), (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
    }

    if (!rectangleMode_hasLetGo) {
        if (!first)  outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.bitmapActionData.add( outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!)
        first = false
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null
        rectangleMode_hasLetGo = false
        first = true
    }

    if (rectangleOrigin == null) {
        rectangleOrigin = coordinatesTapped
    } else {
        rectangleAlgorithmInstance.compute(rectangleOrigin!!, coordinatesTapped)
    }
}