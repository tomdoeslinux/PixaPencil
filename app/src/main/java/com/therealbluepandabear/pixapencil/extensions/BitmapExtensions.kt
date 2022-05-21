package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Bitmap
import android.graphics.Matrix
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

    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, false)
}

fun Bitmap.clone(): Bitmap {
    return this.copy(this.config, this.isMutable)
}