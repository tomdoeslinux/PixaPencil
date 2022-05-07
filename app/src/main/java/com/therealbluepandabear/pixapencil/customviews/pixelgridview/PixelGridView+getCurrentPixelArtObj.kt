package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.PixelArt

fun PixelGridView.extendedGetCurrentPixelArtObj(): PixelArt {
    val pixelArtData = AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreationsNoLiveData()

    return pixelArtData[currentIndex]
}