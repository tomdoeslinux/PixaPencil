package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedSaveProject() {
    saved = true

    val bmp = getCoverImageBitmap()

    if (index == -1) {
        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.pixelArtCreationsDao().insertPixelArt(
                PixelArt(BitmapConverter.convertBitmapToString(bmp), BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap), spanCount, spanCount, outerCanvasInstance.getCurrentRotation(), title.toString(),false))
        }
        (this as Activity).onBackPressed()
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()

        AppData.pixelArtDB.pixelArtCreationsDao().apply {
            updatePixelArtCreationCoverBitmap(BitmapConverter.convertBitmapToString(bmp), currentPixelArtObj.objId)
            updatePixelArtCreationBitmap(BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap), currentPixelArtObj.objId)
            updatePixelArtCreationRotation(outerCanvasInstance.getCurrentRotation(), currentPixelArtObj.objId)
        }
        outerCanvasInstance.rotate(0)
        (this as Activity).onBackPressed()
    }
}