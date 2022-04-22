package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates

private fun PixelGridView.setPixelAndSaveToBitmapAction(coordinates: Coordinates, color: Int, saveToBitmapAction: Boolean = true) {
    if (saveToBitmapAction) {
        undoStack.clear()
        pixelGridViewInstance.currentBitmapAction!!.actionData.add(
            BitmapActionData(
                coordinates,
                pixelGridViewBitmap.getPixel(coordinates.x, coordinates.y),
                color
            )
        )
    }

    pixelGridViewBitmap.setPixel(coordinates.x, coordinates.y, color)
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
            horizontallyMirroredCoordinates = Coordinates(x, (pixelGridViewBitmap.height - y) - 1)
            verticallyMirroredCoordinates = Coordinates((pixelGridViewBitmap.width - x) - 1, y)
            quadMirroredCoordinates = Coordinates((pixelGridViewBitmap.width - x) - 1, (pixelGridViewBitmap.height - y) - 1)
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