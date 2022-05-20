package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

var lineOrigin: Coordinates? = null
var rectangleOrigin: Coordinates? = null
var circleOrigin: Coordinates? = null
var polygonCoordinates = mutableListOf<Coordinates>()
var cindx = 0
var first = true

fun CanvasActivity.extendedOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!primaryAlgorithmInfoParameterInitialized) {
        initPrimaryAlgorithmInfoParameter()
    }

    saved = false
    when (currentTool) {
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
            rectangleToolOnPixelTapped(coordinatesTapped, false)
        }

        Tool.OutlinedRectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, true)
        }

        Tool.SquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, false)
        }

        Tool.OutlinedSquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, true)
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
            pixelGridViewInstance.shadingMode = true
            pencilToolOnPixelTapped(coordinatesTapped)
        }
    }
}