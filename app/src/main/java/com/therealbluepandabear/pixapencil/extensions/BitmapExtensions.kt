package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import com.therealbluepandabear.pixapencil.enums.OverlayType
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.MatrixInfo

fun Bitmap.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    for (i_1 in 0 until width) {
        for (i_2 in 0 until height) {
            if (getPixel(i_1, i_2) == colorToFind) {
                setPixel(i_1, i_2, colorToReplace)
            }
        }
    }
}

fun Bitmap.calculateMatrix(newWidth: Float, newHeight: Float): MatrixInfo {
    val scaleWidth = newWidth / width
    val scaleHeight = newHeight / height

    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)

    return MatrixInfo(matrix, scaleWidth, scaleHeight)
}

fun Bitmap.setPixel(coordinates: Coordinates, color: Int) {
    return setPixel(coordinates.x, coordinates.y, color)
}

fun Bitmap.getPixel(coordinates: Coordinates): Int {
    return getPixel(coordinates.x, coordinates.y)
}

fun Bitmap.isWidthLarger(): Boolean {
    return width > height
}

fun Bitmap.isRect(): Boolean {
    return width != height
}

fun Bitmap.rotate(degrees: Int): Bitmap {
    val matrix = Matrix()
    matrix.setRotate(degrees.toFloat())

    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
}

fun Bitmap.clone(): Bitmap {
    return copy(config, isMutable)
}

fun Bitmap.createMutableClone(): Bitmap {
    return copy(config, true)
}

fun Bitmap.overlay(bmp2: Bitmap, overlayType: OverlayType = OverlayType.Regular): Bitmap {
    val bitmapOverlay = Bitmap.createBitmap(width, height, config)
    val canvas = Canvas(bitmapOverlay)

    if (overlayType == OverlayType.Regular) {
        canvas.drawBitmap(this, Matrix(), null)
        canvas.drawBitmap(bmp2, 0f, 0f, null)
    } else {
        val startX = (canvas.width - bmp2.width) / 2
        val startY = (canvas.height - bmp2.height) / 2

        canvas.drawBitmap(this, Matrix(), null)
        canvas.drawBitmap(bmp2, startX.toFloat(), startY.toFloat(), null)
    }

    recycle()
    bmp2.recycle()

    return bitmapOverlay
}

fun Bitmap.isEmpty(): Boolean {
    return (Bitmap.createBitmap(width, height, config).sameAs(this))
}
