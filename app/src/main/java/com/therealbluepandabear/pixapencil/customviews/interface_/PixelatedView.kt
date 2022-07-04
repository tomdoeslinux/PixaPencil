package com.therealbluepandabear.pixapencil.customviews.interface_

import android.content.Context
import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.constants.ObjectConstants

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
            val pixelArtData = AppData.pixelArtDB.dao().getAllNoLiveData()

            val gcbCurrentPixelArtObj = pixelArtData[currentIndex]

            ObjectConstants.CurrentPixelArtObj = gcbCurrentPixelArtObj

            return BitmapConverter.convertStringToBitmap(gcbCurrentPixelArtObj.bitmap)!!
        }
        throw IllegalArgumentException(context.getString(R.string.exception_accessing_negative_index_message_in_code_str))
    }

    fun getCurrentPixelArtObj(): PixelArt {
        val pixelArtData = AppData.pixelArtDB.dao().getAllNoLiveData()

        return pixelArtData[currentIndex]
    }
}