/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import com.therealbluepandabear.pixapencil.utility.general.ColorFilterUtilities

fun CanvasActivity.CanvasCommandsHelper.extendedSetPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true, ignoreShadingMap: Boolean = false) {
    baseReference.viewModel.redoStack.clear()

    val colorAtCoordinates = baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(coordinates)

    if ((saveToBitmapAction && !baseReference.binding.activityCanvasDrawingView.shadingMode) || (baseReference.binding.activityCanvasDrawingView.shadingMode && ignoreShadingMap)) {
        baseReference.viewModel.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, color))
        baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.setPixel(coordinates, color)
    }
    else if (baseReference.binding.activityCanvasDrawingView.shadingMode && !baseReference.binding.activityCanvasDrawingView.shadingMap.contains(coordinates) && colorAtCoordinates != Color.TRANSPARENT && !ignoreShadingMap) {
        val shadeColor = if (baseReference.shadingToolMode == StringConstants.ShadingToolModes.LIGHTEN_SHADING_TOOL_MODE) {
            Color.WHITE
        } else {
            Color.BLACK
        }

        val newColor = ColorFilterUtilities.blendColor(colorAtCoordinates, shadeColor, 0.2f)
        baseReference.viewModel.currentBitmapAction!!.actionData.add(BitmapActionData(coordinates, colorAtCoordinates, newColor))
        baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.setPixel(coordinates, newColor)
        baseReference.binding.activityCanvasDrawingView.shadingMap.add(coordinates)
    }

    baseReference.viewModel.currentBitmap = baseReference.binding.activityCanvasDrawingView.drawingViewBitmap
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
    with(baseReference.binding.activityCanvasDrawingView) {
        var horizontallyReflectedCoordinates: Coordinates? = null
        var verticallyReflectedCoordinates: Coordinates? = null
        var quadMirroredCoordinates = listOf<Coordinates>()
        var octalMirroredCoordinates = listOf<Coordinates>()

        when {
            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Horizontal && !ignoreSymmetry -> {
                horizontallyReflectedCoordinates =
                    coordinates.getHorizontallyReflectedCoordinates(drawingViewBitmap.height)
            }

            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Vertical && !ignoreSymmetry -> {
                verticallyReflectedCoordinates =
                    coordinates.getVerticallyReflectedCoordinates(drawingViewBitmap.width)
            }

            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Quad && !ignoreSymmetry -> {
                quadMirroredCoordinates = coordinates.getQuadReflectedCoordinateSet(
                    drawingViewBitmap.width,
                    drawingViewBitmap.height
                )
            }

            baseReference.viewModel.currentSymmetryMode == SymmetryMode.Octal && !ignoreSymmetry -> {
                octalMirroredCoordinates = coordinates.getOctalReflectedCoordinateSet(
                    drawingViewBitmap.width,
                    drawingViewBitmap.height
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