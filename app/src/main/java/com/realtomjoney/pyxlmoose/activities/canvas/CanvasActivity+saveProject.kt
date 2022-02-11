package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedSaveProject() {
    saved = true

    val bmp = outerCanvasInstance.fragmentHost.drawToBitmap()
    val bmps: Bitmap?

    val matrix = Matrix()
    matrix.setRotate(outerCanvasInstance.getCurrentRotation())

    bmps = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.width, matrix, false)

    if (index == -1) {
        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.pixelArtCreationsDao().insertPixelArt(
                PixelArt(BitmapConverter.convertBitmapToString(bmps), BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap), spanCount, spanCount, outerCanvasInstance.getCurrentRotation(), title.toString(),false))
        }
        (this as Activity).onBackPressed()
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.invalidate()

        AppData.pixelArtDB.pixelArtCreationsDao().apply {
            updatePixelArtCreationCoverBitmap(BitmapConverter.convertBitmapToString(bmps), currentPixelArtObj.objId)
            updatePixelArtCreationBitmap(BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap), currentPixelArtObj.objId)
            updatePixelArtCreationRotation(outerCanvasInstance.getCurrentRotation(), currentPixelArtObj.objId)
        }
        outerCanvasInstance.rotate(0)
        (this as Activity).onBackPressed()
    }
}