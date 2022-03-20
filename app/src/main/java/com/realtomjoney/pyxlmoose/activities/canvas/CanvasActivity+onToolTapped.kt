package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.utility.Flags
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    if (currentTool == Tools.POLYGON_TOOL && toolName != StringConstants.PolygonToolIdentifier) {
        Flags.DISABLE_ACTION_MOVE = false
        polygonCoordinates.clear()
        cindx = 0
    }

    when (toolName) {
        StringConstants.PencilToolIdentifier -> currentTool = Tools.PENCIL_TOOL

        StringConstants.FillToolIdentifier  -> currentTool = Tools.FILL_TOOL

        StringConstants.VerticalMirrorToolIdentifier  -> currentTool = Tools.VERTICAL_MIRROR_TOOL

        StringConstants.HorizontalMirrorToolIdentifier -> currentTool = Tools.HORIZONTAL_MIRROR_TOOL

        StringConstants.LineToolIdentifier -> currentTool = Tools.LINE_TOOL

        StringConstants.RectangleToolIdentifier -> currentTool = Tools.RECTANGLE_TOOL

        StringConstants.OutlinedRectangleToolIdentifier -> currentTool = Tools.OUTLINED_RECTANGLE_TOOL

        StringConstants.SquareToolIdentifier -> currentTool = Tools.SQUARE_TOOL

        StringConstants.OutlinedSquareToolIdentifier -> currentTool = Tools.OUTLINED_SQUARE_TOOL

        StringConstants.SprayToolIdentifier -> sprayToolOnToolTapped()

        StringConstants.PolygonToolIdentifier -> {
            if (currentTool == Tools.POLYGON_TOOL) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null

                polygonCoordinates.clear()
                cindx = 0
            }
            currentTool = Tools.POLYGON_TOOL
        }

        StringConstants.DitherToolIdentifier -> {
            currentTool = Tools.DITHER_TOOL
        }

        StringConstants.DarkenToolIdentifier  -> {
            filterSelectedColor(Color.BLACK, 0.2f)
            currentTool = Tools.DARKEN_TOOL
        }
        StringConstants.LightenToolIdentifier  -> {
            filterSelectedColor(Color.WHITE, 0.2f)
            currentTool = Tools.LIGHTEN_TOOL
        }
        StringConstants.ClearCanvasToolIdentifier  -> {
            showDialog(
                StringConstants.DialogClearCanvasTitle,
                StringConstants.DialogClearCanvasMessage,
                StringConstants.DialogPositiveButtonText,
                { _, _ ->
                    clearCanvas()
                }, StringConstants.DialogNegativeButtonText, { _, _ -> }, null)
        }

        StringConstants.ColorPickerToolIdentifier -> currentTool = Tools.COLOR_PICKER_TOOL

        StringConstants.FindAndReplaceToolIdentifier  -> findAndReplaceToolOnToolTapped()

        StringConstants.EraseToolIdentifier -> currentTool = Tools.ERASE_TOOL

        StringConstants.GridToolIdentifier -> {
            if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.canvasHeight <= 150) {
                if (!outerCanvasInstance.canvasFragment.myCanvasViewInstance.gridEnabled) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.showGrid()
                } else {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.hideGrid()
                }
            } else {
                binding.activityCanvasRootLayout.showSnackbar("PyxlMoose currently does not support showing a grid for canvas sizes of over 150", SnackbarDuration.DEFAULT)
            }
        }
    }
}