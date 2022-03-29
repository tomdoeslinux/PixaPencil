package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.models.Coordinates

var lineOrigin: Coordinates? = null
var rectangleOrigin: Coordinates? = null
var circleOrigin: Coordinates? = null
var polygonCoordinates = mutableListOf<Coordinates>()
var cindx = 0
var first = true

fun extendedOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!primaryAlgorithmInfoParameterInitialized) {
        initPrimaryAlgorithmInfoParameter()
    }

    saved = false
    when (currentTool) {
        Tools.PencilTool -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        Tools.VerticalMirrorTool -> {
            verticalMirrorToolOnPixelTapped(coordinatesTapped)
        }

        Tools.HorizontalMirrorTool -> {
            horizontalMirrorToolOnPixelTapped(coordinatesTapped)
        }

        Tools.FillTool -> {
            fillToolOnPixelTapped(coordinatesTapped)
        }

        Tools.LineTool -> {
            lineToolOnPixelTapped(coordinatesTapped)
        }

        Tools.RectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, false)
        }

        Tools.OutlinedRectangleTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, true)
        }

        Tools.SquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, false)
        }

        Tools.OutlinedSquareTool -> {
            rectangleToolOnPixelTapped(coordinatesTapped, true)
        }

        Tools.CircleTool -> {
            circleToolOnPixelTapped(coordinatesTapped)
        }

        Tools.OutlinedCircleTool -> {
            circleToolOnPixelTapped(coordinatesTapped)
        }

        Tools.SprayTool -> {
            sprayToolOnPixelTapped(coordinatesTapped)
        }

        Tools.PolygonTool -> {
            polygonToolOnPixelTapped(coordinatesTapped)
        }

        Tools.DitherTool -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }

        Tools.EraseTool -> {
            eraseToolOnPixelTapped(coordinatesTapped)
        }

        Tools.ColorPickerTool -> {
            colorPickerToolOnPixelTapped(coordinatesTapped)
        }

        else -> {
            pencilToolOnPixelTapped(coordinatesTapped)
        }
    }
}