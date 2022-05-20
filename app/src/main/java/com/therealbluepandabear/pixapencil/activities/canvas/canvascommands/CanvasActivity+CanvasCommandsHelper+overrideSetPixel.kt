package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.shadingToolMode
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities

fun CanvasActivity.extendedSetPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true) {
    viewModel.undoStack.clear()

    val colorAtCoordinates = pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinates)

    if (saveToBitmapAction && !pixelGridViewInstance.shadingMode) {
        viewModel.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, color))
        pixelGridViewInstance.pixelGridViewBitmap.setPixel(coordinates, color)
    } else if (pixelGridViewInstance.shadingMode && !pixelGridViewInstance.shadingMap.contains(coordinates) && colorAtCoordinates != Color.TRANSPARENT) {
        val shadeColor = if (shadingToolMode == "Lighten") {
            Color.WHITE
        } else {
            Color.BLACK
        }

        val newColor = ColorFilterUtilities.blendColor(colorAtCoordinates, shadeColor, 0.2f)
        viewModel.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, newColor))
        pixelGridViewInstance.pixelGridViewBitmap.setPixel(coordinates, newColor)
        pixelGridViewInstance.shadingMap.add(coordinates)
    }
}

fun CanvasActivity.CanvasCommandsHelper.overrideSetPixel(
    coordinates: Coordinates,
    color: Int,
    ignoreBrush: Boolean = false,
    saveToBitmapAction: Boolean = true,
    ignoreSymmetry: Boolean = false,
    ignoreDither: Boolean = false
) {
    with(pixelGridViewInstance) {
        var horizontallyReflectedCoordinates: Coordinates? = null
        var verticallyReflectedCoordinates: Coordinates? = null
        var quadMirroredCoordinates = listOf<Coordinates>()
        var octalMirroredCoordinates = listOf<Coordinates>()

        when {
            symmetryMode == SymmetryMode.Horizontal && !ignoreSymmetry -> {
                horizontallyReflectedCoordinates =
                    coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height)
            }

            symmetryMode == SymmetryMode.Vertical && !ignoreSymmetry -> {
                verticallyReflectedCoordinates =
                    coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width)
            }

            symmetryMode == SymmetryMode.Quad && !ignoreSymmetry -> {
                quadMirroredCoordinates = coordinates.getQuadReflectedCoordinateSet(
                    pixelGridViewBitmap.width,
                    pixelGridViewBitmap.height
                )
            }

            symmetryMode == SymmetryMode.Octal && !ignoreSymmetry -> {
                octalMirroredCoordinates = coordinates.getOctalReflectedCoordinateSet(
                    pixelGridViewBitmap.width,
                    pixelGridViewBitmap.height
                )
            }

            else -> {}
        }

        if (coordinatesInCanvasBounds(coordinates, ignoreDither)) {
            setPixelAndSaveToBitmapActionReference(coordinates, color)

            if (horizontallyReflectedCoordinates != null && coordinatesInCanvasBounds(horizontallyReflectedCoordinates, ignoreDither)) {
                setPixelAndSaveToBitmapActionReference(horizontallyReflectedCoordinates, color)
            }

            if (verticallyReflectedCoordinates != null && coordinatesInCanvasBounds(verticallyReflectedCoordinates, ignoreDither)) {
                setPixelAndSaveToBitmapActionReference(verticallyReflectedCoordinates, color)
            }

            if (quadMirroredCoordinates.isNotEmpty()) {
                for (coordinate in quadMirroredCoordinates) {
                    if (coordinatesInCanvasBounds(coordinate, ignoreDither)) {
                        setPixelAndSaveToBitmapActionReference(coordinate, color)
                    }
                }
            }

            if (octalMirroredCoordinates.isNotEmpty()) {
                for (coordinate in octalMirroredCoordinates) {
                    if (coordinatesInCanvasBounds(coordinate, ignoreDither)) {
                        setPixelAndSaveToBitmapActionReference(coordinate, color)
                    }
                }
            }

            if (currentBrush != null && !ignoreBrush) {
                for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2, ignoreDither)) {
                        setPixelAndSaveToBitmapActionReference(xyPosition_2, color, saveToBitmapAction)
                    }
                }

                if (horizontallyReflectedCoordinates != null) {
                    for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(horizontallyReflectedCoordinates)) {
                        if (coordinatesInCanvasBounds(xyPosition_2, ignoreDither)) {
                            setPixelAndSaveToBitmapActionReference(xyPosition_2, color, saveToBitmapAction)
                        }
                    }
                }

                if (verticallyReflectedCoordinates != null) {
                    for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(verticallyReflectedCoordinates)) {
                        if (coordinatesInCanvasBounds(xyPosition_2, ignoreDither)) {
                            setPixelAndSaveToBitmapActionReference(xyPosition_2, color, saveToBitmapAction)
                        }
                    }
                }

                if (quadMirroredCoordinates.isNotEmpty()) {
                    for (coordinates_1 in quadMirroredCoordinates) {
                        for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates_1)) {
                            if (coordinatesInCanvasBounds(xyPosition_2, ignoreDither)) {
                                setPixelAndSaveToBitmapActionReference(xyPosition_2, color, saveToBitmapAction)
                            }
                        }
                    }
                }

                if (octalMirroredCoordinates.isNotEmpty()) {
                    for (coordinates_1 in octalMirroredCoordinates) {
                        for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates_1)) {
                            if (coordinatesInCanvasBounds(xyPosition_2, ignoreDither)) {
                                setPixelAndSaveToBitmapActionReference(xyPosition_2, color, saveToBitmapAction)
                            }
                        }
                    }
                }
            }
        }
    }
}