package com.therealbluepandabear.pyxlmoose.extensions

import android.graphics.Bitmap
import android.graphics.Matrix
import com.therealbluepandabear.pyxlmoose.models.MatrixInfo

fun Bitmap.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    for (i_1 in 0 until this.width) {
        for (i_2 in 0 until this.height) {
            if (this.getPixel(i_1, i_2) == colorToFind) {
                this.setPixel(i_1, i_2, colorToReplace)
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