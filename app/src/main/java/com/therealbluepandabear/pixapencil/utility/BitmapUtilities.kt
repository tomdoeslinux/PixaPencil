package com.therealbluepandabear.pixapencil.utility

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import com.therealbluepandabear.pixapencil.enums.OverlayType

object BitmapUtilities {
    /** Thank you to to RajaReddy PolamReddy on StackOverflow - the code here is  based off of their solution.
     *
     * - [Link to RajaReddy PolamReddy's profile](https://stackoverflow.com/users/964741/rajareddy-polamreddy)
     * - [Original StackOverFlow post](https://stackoverflow.com/questions/10616777/how-to-merge-to-two-bitmap-one-over-another)
     * **/

    fun overlay(bmp1: Bitmap, bmp2: Bitmap, overlayType: OverlayType = OverlayType.Regular): Bitmap {
        val bitmapOverlay = Bitmap.createBitmap(bmp1.width, bmp1.height, bmp1.config)
        val canvas = Canvas(bitmapOverlay)

        if (overlayType == OverlayType.Regular) {
            canvas.drawBitmap(bmp1, Matrix(), null)
            canvas.drawBitmap(bmp2, 0f, 0f, null)
        } else {
            val startX = (canvas.width - bmp2.width) / 2
            val startY = (canvas.height - bmp2.height) / 2

            canvas.drawBitmap(bmp1, Matrix(), null)
            canvas.drawBitmap(bmp2, startX.toFloat(), startY.toFloat(), null)
        }

        bmp1.recycle()
        bmp2.recycle()

        return bitmapOverlay
    }


    fun resize(bmp: Bitmap, ratio: Double): Bitmap {
        return Bitmap.createScaledBitmap(
            bmp, (bmp.width * ratio).toInt(),
            (bmp.height * ratio).toInt(),
            false
        )
    }
}