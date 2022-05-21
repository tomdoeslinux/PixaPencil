package com.therealbluepandabear.pixapencil.customviews.interface_

import android.content.Context
import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.activities.canvas.currentPixelArtObj
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
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

    var pixelArtId : Int

    fun getCurrentBitmap(context: Context): Bitmap {
        if (pixelArtId != -1) {
            currentPixelArtObj = getCurrentPixelArtObjById(pixelArtId)
            return BitmapConverter.convertStringToBitmap(currentPixelArtObj.bitmap)!!
        }
        throw IllegalArgumentException("Cannot access pixel art object with a negative id")
    }

    fun getCurrentPixelArtObjById(id:Int): PixelArt{
        return AppData.pixelArtDB.pixelArtCreationsDao().getPixelArtWIthId(id)
    }

}