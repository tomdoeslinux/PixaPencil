package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.shadingToolMode
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities

private fun PixelGridView.setPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true) {
    undoStack.clear()

    val colorAtCoordinates = pixelGridViewBitmap.getPixel(coordinates)

    if (saveToBitmapAction && !shadingMode) {
        pixelGridViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, color))
        pixelGridViewBitmap.setPixel(coordinates, color)
    } else if (shadingMode && !shadingMap.contains(coordinates) && colorAtCoordinates != Color.TRANSPARENT){
        val shadeColor = if (shadingToolMode == "Lighten") {
            Color.WHITE
        } else {
            Color.BLACK
        }
        
        val newColor = ColorFilterUtilities.blendColor(colorAtCoordinates, shadeColor, 0.2f)
        pixelGridViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, newColor))
        pixelGridViewBitmap.setPixel(coordinates, newColor)
        shadingMap.add(coordinates)
    }
}

fun PixelGridView.extendedOverrideSetPixel(
    x: Int,
    y: Int,
    color: Int,
    ignoreBrush: Boolean = false,
    saveToBitmapAction: Boolean = true,
    ignoreSymmetry: Boolean = false,
) {
    val coordinates = Coordinates(x, y)

    var horizontallyMirroredCoordinates: Coordinates? = null
    var verticallyMirroredCoordinates: Coordinates? = null
    var quadMirroredCoordinates: Coordinates? = null

    when {
        symmetryMode == SymmetryMode.Horizontal && !ignoreSymmetry -> {
            horizontallyMirroredCoordinates = Coordinates(x, (pixelGridViewBitmap.height - y) - 1)
        }

        symmetryMode == SymmetryMode.Vertical && !ignoreSymmetry -> {
            verticallyMirroredCoordinates = Coordinates((pixelGridViewBitmap.width - x) - 1, y)
        }

        symmetryMode == SymmetryMode.Quad && !ignoreSymmetry -> {
            horizontallyMirroredCoordinates = coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height)
            verticallyMirroredCoordinates = coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width)
            quadMirroredCoordinates = Coordinates(
                coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width).x,
                coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height).y)
        }

        else -> { }
    }

    if (coordinatesInCanvasBounds(coordinates)) {
        setPixelAndSaveToBitmapAction(coordinates, color)

        if (horizontallyMirroredCoordinates != null) {
            setPixelAndSaveToBitmapAction(horizontallyMirroredCoordinates, color)
        }

        if (verticallyMirroredCoordinates != null) {
            setPixelAndSaveToBitmapAction(verticallyMirroredCoordinates, color)
        }

        if (quadMirroredCoordinates != null) {
            setPixelAndSaveToBitmapAction(quadMirroredCoordinates, color)
        }

        if (currentBrush != null && !ignoreBrush) {
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                if (coordinatesInCanvasBounds(xyPosition_2)) {
                    setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                }
            }

            if (horizontallyMirroredCoordinates != null) {
                for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(horizontallyMirroredCoordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2)) {
                        setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                    }
                }
            }

            if (verticallyMirroredCoordinates != null) {
                for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(verticallyMirroredCoordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2)) {
                        setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                    }
                }
            }

            if (quadMirroredCoordinates != null) {
                for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(quadMirroredCoordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2)) {
                        setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                    }
                }
            }
        }
    }
}