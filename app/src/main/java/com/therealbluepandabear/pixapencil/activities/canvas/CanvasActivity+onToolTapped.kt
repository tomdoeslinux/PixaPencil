package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    if (currentTool == Tools.PolygonTool && toolName != StringConstants.PolygonToolIdentifier) {
        Flags.DisableActionMove = false
        polygonCoordinates.clear()
        cindx = 0
    }

    when (toolName) {
        StringConstants.PencilToolIdentifier -> {
            currentTool = Tools.PencilTool
        }

        StringConstants.FillToolIdentifier  -> {
            currentTool = Tools.FillTool
        }

        StringConstants.VerticalMirrorToolIdentifier -> {
            currentTool = Tools.VerticalMirrorTool
        }

        StringConstants.HorizontalMirrorToolIdentifier -> {
            currentTool = Tools.HorizontalMirrorTool
        }

        StringConstants.LineToolIdentifier -> {
            currentTool = Tools.LineTool
        }

        StringConstants.RectangleToolIdentifier -> {
            currentTool = Tools.RectangleTool
        }

        StringConstants.OutlinedRectangleToolIdentifier -> {
            currentTool = Tools.OutlinedRectangleTool
        }

        StringConstants.SquareToolIdentifier -> {
            currentTool = Tools.SquareTool
        }

        StringConstants.OutlinedSquareToolIdentifier -> {
            currentTool = Tools.OutlinedSquareTool
        }

        StringConstants.CircleToolIdentifier -> {
            currentTool = Tools.CircleTool
        }

        StringConstants.OutlinedCircleToolIdentifier -> {
            currentTool = Tools.OutlinedCircleTool
        }

        StringConstants.SprayToolIdentifier -> {
            sprayToolOnToolTapped()
        }

        StringConstants.PolygonToolIdentifier -> {
            if (currentTool == Tools.PolygonTool) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null

                polygonCoordinates.clear()
                cindx = 0
            }
            currentTool = Tools.PolygonTool
        }

        StringConstants.DitherToolIdentifier -> {
            currentTool = Tools.DitherTool
        }

        StringConstants.DarkenToolIdentifier  -> {
            filterSelectedColor(Color.BLACK, 0.2f)
            currentTool = Tools.DarkenTool
        }

        StringConstants.LightenToolIdentifier  -> {
            filterSelectedColor(Color.WHITE, 0.2f)
            currentTool = Tools.LightenTool
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

        StringConstants.ColorPickerToolIdentifier -> {
            currentTool = Tools.ColorPickerTool
        }

        StringConstants.FindAndReplaceToolIdentifier -> {
            findAndReplaceToolOnToolTapped()
        }

        StringConstants.EraseToolIdentifier -> {
            currentTool = Tools.EraseTool
        }

        StringConstants.GridToolIdentifier -> {
            if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.canvasHeight <= 150) {
                if (!outerCanvasInstance.canvasFragment.myCanvasViewInstance.gridEnabled) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.showGrid()
                } else {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.hideGrid()
                }
            } else {
                binding.activityCanvasRootLayout.showSnackbar("PixaPencil currently does not support showing a grid for canvas sizes of over 150", SnackbarDuration.Default)
            }
        }
    }
}