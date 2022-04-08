package com.therealbluepandabear.pixapencil.utility

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix

object BitmapUtilities {
    /** Thank you to to RajaReddy PolamReddy on StackOverflow - the code here is  based off of their solution.
     *
     * - [Link to RajaReddy PolamReddy's profile](https://stackoverflow.com/users/964741/rajareddy-polamreddy)
     * - [Original StackOverFlow post](https://stackoverflow.com/questions/10616777/how-to-merge-to-two-bitmap-one-over-another)
     * **/

    fun overlay(bmp1: Bitmap, bmp2: Bitmap): Bitmap {
        val bimapOverlay = Bitmap.createBitmap(bmp1.width, bmp1.height, bmp1.config)
        val canvas = Canvas(bimapOverlay)

        canvas.drawBitmap(bmp1, Matrix(), null)
        canvas.drawBitmap(bmp2, 0f, 0f, null)
        bmp1.recycle()
        bmp2.recycle()

        return bimapOverlay
    }
}