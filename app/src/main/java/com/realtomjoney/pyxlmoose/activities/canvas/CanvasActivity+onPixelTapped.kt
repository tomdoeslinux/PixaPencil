package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.SprayAlgorithm
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.Flags

var lineOrigin: Coordinates? = null
var rectangleOrigin: Coordinates? = null
var polygonCoordinates = mutableListOf<Coordinates>()
var cindx = 0
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
        Tools.POLYGON_TOOL -> {
            Flags.DISABLE_ACTION_MOVE = true

            val lineAlgorithmInstance = LineAlgorithm(
                AlgorithmInfoParameter(
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
                    BitmapAction(mutableListOf()),
                    getSelectedColor()
                )
            )

            polygonCoordinates.add(coordinatesTapped)

            if (polygonCoordinates.size > 1) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()

                for (i in 0 until polygonCoordinates.size - 2) {
                    lineAlgorithmInstance.compute(
                        polygonCoordinates[cindx - (i + 1)],
                        polygonCoordinates[cindx - i]
                    )
                }

                lineAlgorithmInstance.compute(polygonCoordinates[cindx], polygonCoordinates[cindx + 1])
                lineAlgorithmInstance.compute(polygonCoordinates[0], polygonCoordinates[cindx + 1])

                cindx += 1
            }
        }
        Tools.ERASE_TOOL -> eraseToolOnPixelTapped(coordinatesTapped)
        Tools.COLOR_PICKER_TOOL -> colorPickerToolOnPixelTapped(coordinatesTapped)
        else -> pencilToolOnPixelTapped(coordinatesTapped)
    }
}