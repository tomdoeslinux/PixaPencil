package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import android.util.Log
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.general.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.CanvasCommandsHelper.extendedSetPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true, ignoreShadingMap: Boolean = false) {
    baseReference.viewModel.undoStack.clear()

    val colorAtCoordinates = pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinates)

    if ((saveToBitmapAction && !pixelGridViewInstance.shadingMode) || (pixelGridViewInstance.shadingMode && ignoreShadingMap)) {
        baseReference.viewModel.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, color))
        pixelGridViewInstance.pixelGridViewBitmap.setPixel(coordinates, color)
    } else if (pixelGridViewInstance.shadingMode && !pixelGridViewInstance.shadingMap.contains(coordinates) && colorAtCoordinates != Color.TRANSPARENT && !ignoreShadingMap) {
        val shadeColor = if (baseReference.shadingToolMode == StringConstants.ShadingToolModes.LIGHTEN_SHADING_TOOL_MODE) {
            Color.WHITE
        } else {
            Color.BLACK
        }

        val newColor = ColorFilterUtilities.blendColor(colorAtCoordinates, shadeColor, 0.2f)
        baseReference.viewModel.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, newColor))
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
    ignoreDither: Boolean = false,
    ignoreShadingMap: Boolean = false,
) {
    with(pixelGridViewInstance) {

        Log.d("CIRCLE", "assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(${coordinates.x}, ${coordinates.y})) == Color.BLACK)")
        var horizontallyReflectedCoordinates: Coordinates? = null
        var verticallyReflectedCoordinates: Coordinates? = null
        var quadMirroredCoordinates = listOf<Coordinates>()
        var octalMirroredCoordinates = listOf<Coordinates>()


        when {
            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Horizontal && !ignoreSymmetry -> {
                horizontallyReflectedCoordinates =
                    coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height)
            }

            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Vertical && !ignoreSymmetry -> {
                verticallyReflectedCoordinates =
                    coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width)
            }

            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Quad && !ignoreSymmetry -> {
                quadMirroredCoordinates = coordinates.getQuadReflectedCoordinateSet(
                    pixelGridViewBitmap.width,
                    pixelGridViewBitmap.height
                )
            }

            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Octal && !ignoreSymmetry -> {
                octalMirroredCoordinates = coordinates.getOctalReflectedCoordinateSet(
                    pixelGridViewBitmap.width,
                    pixelGridViewBitmap.height
                )
            }

            else -> {}
        }

        if (coordinatesInCanvasBounds(coordinates, baseReference.viewModel.currentTool, ignoreDither)) {
            extendedSetPixelAndSaveToBitmapAction(coordinates, color, ignoreShadingMap = ignoreShadingMap)

            if (horizontallyReflectedCoordinates != null && coordinatesInCanvasBounds(horizontallyReflectedCoordinates, baseReference.viewModel.currentTool, ignoreDither)) {
                extendedSetPixelAndSaveToBitmapAction(horizontallyReflectedCoordinates, color)
            }

            if (verticallyReflectedCoordinates != null && coordinatesInCanvasBounds(verticallyReflectedCoordinates, baseReference.viewModel.currentTool, ignoreDither)) {
                extendedSetPixelAndSaveToBitmapAction(verticallyReflectedCoordinates, color)
            }

            if (quadMirroredCoordinates.isNotEmpty()) {
                for (coordinate in quadMirroredCoordinates) {
                    if (coordinatesInCanvasBounds(coordinate, baseReference.viewModel.currentTool, ignoreDither)) {
                        extendedSetPixelAndSaveToBitmapAction(coordinate, color)
                    }
                }
            }

            if (octalMirroredCoordinates.isNotEmpty()) {
                for (coordinate in octalMirroredCoordinates) {
                    if (coordinatesInCanvasBounds(coordinate, baseReference.viewModel.currentTool, ignoreDither)) {
                        extendedSetPixelAndSaveToBitmapAction(coordinate, color)
                    }
                }
            }

            if (baseReference.viewModel.currentBrush != BrushesDatabase.toList().first() && !ignoreBrush) {
                for (xyPosition_2 in baseReference.viewModel.currentBrush.convertBrushInstructionDataToXYPositionData(coordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2,baseReference.viewModel.currentTool, ignoreDither)) {
                        extendedSetPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                    }
                }

                if (horizontallyReflectedCoordinates != null) {
                    for (xyPosition_2 in baseReference.viewModel.currentBrush.convertBrushInstructionDataToXYPositionData(horizontallyReflectedCoordinates)) {
                        if (coordinatesInCanvasBounds(xyPosition_2, baseReference.viewModel.currentTool, ignoreDither)) {
                            extendedSetPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                        }
                    }
                }

                if (verticallyReflectedCoordinates != null) {
                    for (xyPosition_2 in baseReference.viewModel.currentBrush.convertBrushInstructionDataToXYPositionData(verticallyReflectedCoordinates)) {
                        if (coordinatesInCanvasBounds(xyPosition_2, baseReference.viewModel.currentTool, ignoreDither)) {
                            extendedSetPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                        }
                    }
                }

                if (quadMirroredCoordinates.isNotEmpty()) {
                    for (coordinates_1 in quadMirroredCoordinates) {
                        for (xyPosition_2 in baseReference.viewModel.currentBrush.convertBrushInstructionDataToXYPositionData(coordinates_1)) {
                            if (coordinatesInCanvasBounds(xyPosition_2, baseReference.viewModel.currentTool, ignoreDither)) {
                                extendedSetPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                            }
                        }
                    }
                }

                if (octalMirroredCoordinates.isNotEmpty()) {
                    for (coordinates_1 in octalMirroredCoordinates) {
                        for (xyPosition_2 in baseReference.viewModel.currentBrush.convertBrushInstructionDataToXYPositionData(coordinates_1)) {
                            if (coordinatesInCanvasBounds(xyPosition_2, baseReference.viewModel.currentTool, ignoreDither)) {
                                extendedSetPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                            }
                        }
                    }
                }
            }
        }
    }
}