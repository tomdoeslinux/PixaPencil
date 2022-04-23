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
    coordinates: Coordinates,
    color: Int,
    ignoreBrush: Boolean = false,
    saveToBitmapAction: Boolean = true,
    ignoreSymmetry: Boolean = false,
) {
    var horizontallyReflectedCoordinates: Coordinates? = null
    var verticallyReflectedCoordinates: Coordinates? = null
    var quadMirroredCoordinates = listOf<Coordinates>()
    var octalMirroredCoordinates = listOf<Coordinates>()

    when {
        symmetryMode == SymmetryMode.Horizontal && !ignoreSymmetry -> {
            horizontallyReflectedCoordinates = coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height)
        }

        symmetryMode == SymmetryMode.Vertical && !ignoreSymmetry -> {
            verticallyReflectedCoordinates =  coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width)
        }

        symmetryMode == SymmetryMode.Quad && !ignoreSymmetry -> {
            quadMirroredCoordinates = coordinates.getQuadReflectedCoordinateSet(pixelGridViewBitmap.width, pixelGridViewBitmap.height)
//            quadMirroredCoordinates.add(coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height))
//            quadMirroredCoordinates.add(coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width))
//            quadMirroredCoordinates.add(Coordinates(
//                coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height).y,
//                coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width).x))
//
//            val coords = Coordinates(coordinates.y, coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width).x)
//            val coords2 = Coordinates(coordinates.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height).y, coordinates.x)
//
//            quadMirroredCoordinates.add(Coordinates(
//                coords.y,
//                coords.x))
//            quadMirroredCoordinates.add(Coordinates(
//                coords2.y,
//                coords2.x))
//
//            val coords_ = coordinates.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width)
//            val coords2_ = coords2.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width)
//
//            quadMirroredCoordinates.add(coords_)
//            quadMirroredCoordinates.add(coords2_)
//
//            quadMirroredCoordinates.add(coords_.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height))
//            quadMirroredCoordinates.add(coords2_.getHorizontallyReflectedCoordinates(pixelGridViewBitmap.height))
//
//            quadMirroredCoordinates.add(coords_.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width))
//            quadMirroredCoordinates.add(coords2_.getVerticallyReflectedCoordinates(pixelGridViewBitmap.width))
        }

        symmetryMode == SymmetryMode.Octal && !ignoreSymmetry -> {
            octalMirroredCoordinates = coordinates.getOctalReflectedCoordinateSet(pixelGridViewBitmap.width, pixelGridViewBitmap.height)
        }

        else -> { }
    }

    if (coordinatesInCanvasBounds(coordinates)) {
        setPixelAndSaveToBitmapAction(coordinates, color)

        if (horizontallyReflectedCoordinates != null) {
            setPixelAndSaveToBitmapAction(horizontallyReflectedCoordinates, color)
        }

        if (verticallyReflectedCoordinates != null) {
            setPixelAndSaveToBitmapAction(verticallyReflectedCoordinates, color)
        }

        if (quadMirroredCoordinates.isNotEmpty()) {
            for (coordinate in quadMirroredCoordinates) {
                setPixelAndSaveToBitmapAction(coordinate, color)
            }
        }

        if (octalMirroredCoordinates.isNotEmpty()) {
            for (coordinate in octalMirroredCoordinates) {
                setPixelAndSaveToBitmapAction(coordinate, color)
            }
        }

        if (currentBrush != null && !ignoreBrush) {
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates)) {
                if (coordinatesInCanvasBounds(xyPosition_2)) {
                    setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                }
            }

            if (horizontallyReflectedCoordinates != null) {
                for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(horizontallyReflectedCoordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2)) {
                        setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                    }
                }
            }

            if (verticallyReflectedCoordinates != null) {
                for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(verticallyReflectedCoordinates)) {
                    if (coordinatesInCanvasBounds(xyPosition_2)) {
                        setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                    }
                }
            }

            if (quadMirroredCoordinates.isNotEmpty()) {
                for (coordinates_1 in quadMirroredCoordinates) {
                    for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates_1)) {
                        if (coordinatesInCanvasBounds(xyPosition_2)) {
                            setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                        }
                    }
                }
            }

            if (octalMirroredCoordinates.isNotEmpty()) {
                for (coordinates_1 in octalMirroredCoordinates) {
                    for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(coordinates_1)) {
                        if (coordinatesInCanvasBounds(xyPosition_2)) {
                            setPixelAndSaveToBitmapAction(xyPosition_2, color, saveToBitmapAction)
                        }
                    }
                }
            }
        }
    }
}