package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import android.view.MenuItem
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    val zoom = 0.8f
    val maxZoomOut = 0.19999999 // TODO - look into why Android is buggy when user zooms out past this number

    when (item.itemId) {
        R.id.zoom_out -> {
            binding.activityCanvasCanvasFragmentHost.apply {
                if (scaleX > maxZoomOut && scaleY > maxZoomOut) {
                    scaleX -= zoom
                    scaleY -= zoom
                }
            }
        }
        R.id.zoom_in -> {
            binding.activityCanvasCanvasFragmentHost.apply {
                scaleX += zoom
                scaleY += zoom
            }
        }
        R.id.save_project -> {
            if (index == -1) {
                CoroutineScope(Dispatchers.IO).launch {
                    AppData.db.pixelArtCreationsDao().insertPixelArt(PixelArt(BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()), binding.activityCanvasCanvasTitleEditText.text.toString(), JsonConverter.convertPixelListToJsonString(canvasFragmentInstance.myCanvasViewInstance.saveData()), false))
                }
                (this as Activity).onBackPressed()
            } else {
                canvasFragmentInstance.myCanvasViewInstance.invalidate()

                AppData.db.pixelArtCreationsDao().apply {
                    updatePixelArtCreationBitmap(BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()), currentPixelArtObj.objId)
                    updatePixelArtCreationPixelData(JsonConverter.convertPixelListToJsonString(canvasFragmentInstance.myCanvasViewInstance.saveData()), currentPixelArtObj.objId)
                }
                (this as Activity).onBackPressed()
            }
        }
    }
    return true
}