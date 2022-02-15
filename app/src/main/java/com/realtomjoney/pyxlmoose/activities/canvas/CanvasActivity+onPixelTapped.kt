package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.SprayAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates

var lineOrigin: Coordinates? = null
var rectangleOrigin: Coordinates? = null
var first = true

fun CanvasActivity.extendedOnPixelTapped(coordinatesTapped: Coordinates) {
    saved = false
    when (currentTool) {
        Tools.PENCIL_TOOL -> pencilToolOnPixelTapped(coordinatesTapped)
        Tools.VERTICAL_MIRROR_TOOL -> verticalMirrorToolOnPixelTapped(coordinatesTapped)
        Tools.HORIZONTAL_MIRROR_TOOL -> horizontalMirrorToolOnPixelTapped(coordinatesTapped)
        Tools.FILL_TOOL -> fillToolOnPixelTapped(coordinatesTapped)
        Tools.LINE_TOOL -> lineToolOnPixelTapped(coordinatesTapped)
        Tools.RECTANGLE_TOOL -> rectangleToolOnPixelTapped(coordinatesTapped, false)
        Tools.OUTLINED_RECTANGLE_TOOL -> rectangleToolOnPixelTapped(coordinatesTapped, true)
        Tools.SPRAY_TOOL -> {
            if (!sprayAlgorithmInstanceInitialized) {
                val s1 = SprayAlgorithm(AlgorithmInfoParameter(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,  outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, extendedGetSelectedColor()))
                sprayAlgorithmInstance = s1
            }

            sprayAlgorithmInstance.compute(coordinatesTapped)
        }
        Tools.ERASE_TOOL -> eraseToolOnPixelTapped(coordinatesTapped)
        Tools.COLOR_PICKER_TOOL -> colorPickerToolOnPixelTapped(coordinatesTapped)
        else -> pencilToolOnPixelTapped(coordinatesTapped)
    }
}