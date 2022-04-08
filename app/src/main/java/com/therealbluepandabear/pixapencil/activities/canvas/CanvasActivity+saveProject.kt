package com.therealbluepandabear.pixapencil.activities.canvas

import android.app.Activity
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedSaveProject() {
    saved = true

    val bmp = getCoverImageBitmap()

    if (index == -1) {
        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.pixelArtCreationsDao().insertPixelArt(
                PixelArt(
                    BitmapConverter.convertBitmapToString(bmp),
                    BitmapConverter.convertBitmapToString(
                        outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap
                    ),
                    width,
                    height,
                    outerCanvasInstance.canvasFragment.pixelGridViewInstance.dimenCW,
                    outerCanvasInstance.canvasFragment.pixelGridViewInstance.dimenCH,
                    outerCanvasInstance.getCurrentRotation(),
                    title.toString(),
                    false
                )
            )
        }
        (this as Activity).onBackPressed()
    } else {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.invalidate()

        AppData.pixelArtDB.pixelArtCreationsDao().apply {
            updatePixelArtCreationCoverBitmap(BitmapConverter.convertBitmapToString(bmp), currentPixelArtObj.objId)
            updatePixelArtCreationBitmap(BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap), currentPixelArtObj.objId)
            updatePixelArtCreationRotation(outerCanvasInstance.getCurrentRotation(), currentPixelArtObj.objId)
        }
        outerCanvasInstance.rotate(0)
        (this as Activity).onBackPressed()
    }
}