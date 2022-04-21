package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    if (currentTool == Tools.PolygonTool && toolName != StringConstants.Identifiers.PolygonToolIdentifier) {
        Flags.DisableActionMove = false
        polygonCoordinates.clear()
        cindx = 0
    }

    when (toolName) {
        StringConstants.Identifiers.PencilToolIdentifier -> {
            currentTool = Tools.PencilTool
        }

        StringConstants.Identifiers.FillToolIdentifier  -> {
            currentTool = Tools.FillTool
        }

        StringConstants.Identifiers.LineToolIdentifier -> {
            currentTool = Tools.LineTool
        }

        StringConstants.Identifiers.RectangleToolIdentifier -> {
            currentTool = Tools.RectangleTool
        }

        StringConstants.Identifiers.OutlinedRectangleToolIdentifier -> {
            currentTool = Tools.OutlinedRectangleTool
        }

        StringConstants.Identifiers.SquareToolIdentifier -> {
            currentTool = Tools.SquareTool
        }

        StringConstants.Identifiers.OutlinedSquareToolIdentifier -> {
            currentTool = Tools.OutlinedSquareTool
        }

        StringConstants.Identifiers.CircleToolIdentifier -> {
            currentTool = Tools.CircleTool
        }

        StringConstants.Identifiers.OutlinedCircleToolIdentifier -> {
            currentTool = Tools.OutlinedCircleTool
        }

        StringConstants.Identifiers.SprayToolIdentifier -> {
            sprayToolOnToolTapped()
        }

        StringConstants.Identifiers.PolygonToolIdentifier -> {
            if (currentTool == Tools.PolygonTool) {
                pixelGridViewInstance.currentBitmapAction = null

                polygonCoordinates.clear()
                cindx = 0
            }
            currentTool = Tools.PolygonTool
        }

        StringConstants.Identifiers.DitherToolIdentifier -> {
            currentTool = Tools.DitherTool
        }

        StringConstants.Identifiers.DarkenToolIdentifier  -> {
            filterSelectedColor(Color.BLACK, 0.2f)
            currentTool = Tools.DarkenTool
        }

        StringConstants.Identifiers.LightenToolIdentifier  -> {
            filterSelectedColor(Color.WHITE, 0.2f)
            currentTool = Tools.LightenTool
        }

        StringConstants.Identifiers.ColorPickerToolIdentifier -> {
            currentTool = Tools.ColorPickerTool
        }

        StringConstants.Identifiers.EraseToolIdentifier -> {
            currentTool = Tools.EraseTool
        }
    }
}