package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedSaveProject() {
    saved = true

    if (index == -1) {
        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.pixelArtCreationsDao().insertPixelArt(
                PixelArt(BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()), BitmapConverter.convertBitmapToString(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap),  spanCount, spanCount, title.toString(),false))
        }
        (this as Activity).onBackPressed()
    } else {
        canvasInstance.myCanvasViewInstance.invalidate()

        AppData.pixelArtDB.pixelArtCreationsDao().apply {
            updatePixelArtCreationCoverBitmap(BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()), currentPixelArtObj.objId)
            updatePixelArtCreationBitmap(BitmapConverter.convertBitmapToString(canvasInstance.myCanvasViewInstance.pixelGridViewBitmap), currentPixelArtObj.objId)
        }
        (this as Activity).onBackPressed()
    }
}