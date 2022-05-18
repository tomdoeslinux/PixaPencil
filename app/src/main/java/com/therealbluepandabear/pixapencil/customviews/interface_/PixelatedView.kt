package com.therealbluepandabear.pixapencil.customviews.interface_

import android.content.Context
import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.currentPixelArtObj
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.customviews.pixelgridview.PixelGridView
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.PixelArt

interface PixelatedView {
    var scaleWidth: Float
    var scaleHeight: Float

    var dimenCW: Int
    var dimenCH: Int

    var st: Boolean

    var canvasWidth: Int
    var canvasHeight: Int

    var currentIndex: Int

    fun getCurrentBitmap(context: Context): Bitmap {
        if (currentIndex != -1) {
            val pixelArtData = AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreationsNoLiveData()

            val gcbCurrentPixelArtObj = pixelArtData[currentIndex]

            currentPixelArtObj = gcbCurrentPixelArtObj

            return BitmapConverter.convertStringToBitmap(currentPixelArtObj.bitmap)!!
        }
        throw IllegalArgumentException(context.getString(R.string.exception_accessing_negative_index_message_in_code_str))
    }

    fun getCurrentPixelArtObj(): PixelArt {
        val pixelArtData = AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreationsNoLiveData()

        return pixelArtData[currentIndex]
    }
}