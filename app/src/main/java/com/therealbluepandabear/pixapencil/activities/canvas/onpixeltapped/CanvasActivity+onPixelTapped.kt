package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates

var polygonCoordinates = mutableListOf<Coordinates>()
var cindx = 0

fun CanvasActivity.extendedOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!primaryAlgorithmInfoParameterInitialized) {
        initPrimaryAlgorithmInfoParameter()
    }

    viewModel.saved = false
    when (viewModel.currentTool) {
        Tool.PencilTool -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        Tool.EraseTool -> {
            eraseToolOnPixelTapped(coordinatesTapped)
        }

        Tool.ColorPickerTool -> {
            colorPickerToolOnPixelTapped(coordinatesTapped)
        }

        Tool.FillTool -> {
            fillToolOnPixelTapped(coordinatesTapped)
        }

        Tool.LineTool -> {
            lineToolOnPixelTapped(coordinatesTapped)
        }

        Tool.RectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedRectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.SquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedSquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.EllipseTool -> {
            ellipseToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedEllipseTool -> {
            ellipseToolOnPixelTapped(coordinatesTapped)
        }

        Tool.CircleTool -> {
            circleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.OutlinedCircleTool -> {
            circleToolOnPixelTapped(coordinatesTapped)
        }

        Tool.SprayTool -> {
            sprayToolOnPixelTapped(coordinatesTapped)
        }

        Tool.PolygonTool -> {
            polygonToolOnPixelTapped(coordinatesTapped)
        }

        Tool.DitherTool -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        Tool.ShadingTool -> {
            binding.activityCanvasPixelGridView.shadingMode = true
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        else -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }
    }
}