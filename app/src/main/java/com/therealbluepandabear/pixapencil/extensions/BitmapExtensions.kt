package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import androidx.core.content.ContextCompat
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.OverlayType
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.MatrixInfo

fun Bitmap.size(): Int {
    return width * height
}

/** Thank you to to confucius on StackOverflow for this solution.
 *
 * - [Link to confucius' profile](https://stackoverflow.com/users/671676/confucius)
 * - [Original StackOverFlow post](https://stackoverflow.com/questions/7237915/replace-black-color-in-bitmap-with-red)
 * **/

fun Bitmap.replacePixelsByColor(colorToFind: Int, colorToReplace: Int, func: ((Coordinates) -> Unit)? = null) {
    val array = IntArray(size())

    getPixels(array, 0, width, 0, 0, width, height)

    for (i in array.indices) {
        if (array[i] == colorToFind) {
            array[i] = colorToReplace

            func?.invoke(Coordinates.fromIndex(i, width))
        }
    }

    setPixels(array, 0, width, 0, 0, width, height)
}

fun Bitmap.getNumberOfUniqueColors(excludeTransparentPixels: Boolean = true): Int {
    val colors = mutableListOf<Int>()
    val array = IntArray(size())

    getPixels(array, 0, width, 0, 0, width, height)

    for (i in array.indices) {
        val color = array[i]

        if (!colors.contains(color) && (excludeTransparentPixels && color != Color.TRANSPARENT)) {
            colors.add(color)
        }
    }

    return colors.size
}

fun Bitmap.filterBitmap(func: (Int) -> Int, func2: (Coordinates, Int) -> Unit) {
    val array = IntArray(size())

    getPixels(array, 0, width, 0, 0, width, height)

    for (i in array.indices) {
        val color = array[i]

        if (color != Color.TRANSPARENT) {
            val filteredColor = func(color)
            func2.invoke(Coordinates.fromIndex(i, width), filteredColor)
        }
    }
}

fun Bitmap.getColors(): MutableList<Int> {
    val colors = mutableListOf<Int>()
    val array = IntArray(size())

    getPixels(array, 0, width, 0, 0, width, height)

    for (i in array.indices) {
        val color = array[i]

        if (!colors.contains(color)) {
            colors.add(color)
        }
    }

    return colors
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
    return Bitmap.createBitmap(width, height, config).sameAs(this)
}

fun Bitmap.clear() {
    eraseColor(Color.TRANSPARENT)
}

fun Bitmap.drawTransparent() {
    val color = Color.parseColor("#d9d9d9")

    for (i_1 in 0 until width) {
        for (i_2 in 0 until height) {
            val coordinates = Coordinates.staticSet(i_1, i_2)

            if (i_1 % 2 == 0) {
                if ((i_1 % 2 == 0) && (i_2 % 2 == 0)) {
                    setPixel(coordinates, color)
                } else {
                    setPixel(coordinates, Color.WHITE)
                }
            } else {
                if ((i_1 % 2 != 0) && (i_2 % 2 != 0)) {
                    setPixel(coordinates, color)
                } else {
                    setPixel(coordinates, Color.WHITE)
                }
            }
        }
    }
}
