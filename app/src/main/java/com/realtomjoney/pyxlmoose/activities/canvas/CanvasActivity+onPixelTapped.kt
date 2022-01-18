package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*
import com.realtomjoney.pyxlmoose.algorithms.FloodFillAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.RectangleAlgorithm
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition

var lineOrigin: XYPosition? = null
var rectangleOrigin: XYPosition? = null
var first = true

fun CanvasActivity.extendedOnPixelTapped(coordinatesTapped: XYPosition) {
    when (currentTool) {
        Tools.PENCIL_TOOL -> pencilToolOnPixelTapped(coordinatesTapped)
        Tools.VERTICAL_MIRROR_TOOL -> verticalMirrorToolOnPixelTapped(coordinatesTapped)
        Tools.HORIZONTAL_MIRROR_TOOL -> horizontalMirrorToolOnPixelTapped(coordinatesTapped)
        Tools.FILL_TOOL -> fillToolOnPixelTapped(coordinatesTapped)
        Tools.LINE_TOOL -> lineToolOnPixelTapped(coordinatesTapped)
        Tools.RECTANGLE_TOOL -> rectangleToolOnPixelTapped(coordinatesTapped, false)
        Tools.OUTLINED_RECTANGLE_TOOL -> rectangleToolOnPixelTapped(coordinatesTapped, true)
        Tools.ERASE_TOOL -> eraseToolOnPixelTapped(coordinatesTapped)
        Tools.COLOR_PICKER_TOOL -> colorPickerToolOnPixelTapped(coordinatesTapped)
        else -> {

        }
    }
}