package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.doSomethingWithChildElements
import com.realtomjoney.pyxlmoose.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.setMenuItemIcon(item: MenuItem, icon: Int, tooltipText: CharSequence? = item.tooltipText) {
    item.icon = ContextCompat.getDrawable(this, icon)
    item.tooltipText = tooltipText
    item.icon.colorFilter = PorterDuffColorFilter(Color.parseColor("#0099cc"), PorterDuff.Mode.SRC_IN)
}

fun enableFullscreen() {
    binding.activityCanvasRootLayout.doSomethingWithChildElements {
        it.visibility = View.GONE
    }
    binding.activityCanvasCanvasFragmentHostCardViewParent.visibility = View.VISIBLE
}

fun disableFullscreen() {
    binding.activityCanvasRootLayout.doSomethingWithChildElements {
        it.visibility = View.VISIBLE
    }
}

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    val zoom = 0.8f
    val maxZoomOut = 0.19999999 // TODO - look into why Android is buggy when user zooms out past this number

    when (item.itemId) {
        R.id.zoom_out -> {
            binding.activityCanvasCanvasFragmentHostCardViewParent.apply {
                if (scaleX > maxZoomOut && scaleY > maxZoomOut) {
                    scaleX -= zoom
                    scaleY -= zoom
                }
            }
        }
        R.id.zoom_in -> {
            binding.activityCanvasCanvasFragmentHostCardViewParent.apply {
                scaleX += zoom
                scaleY += zoom
            }
        }
        R.id.save_project -> {
            if (index == -1) {
                CoroutineScope(Dispatchers.IO).launch {
                    AppData.db.pixelArtCreationsDao().insertPixelArt(PixelArt(BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()), title.toString(), JsonConverter.convertPixelListToJsonString(canvasFragmentInstance.myCanvasViewInstance.saveData()), false))
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
        R.id.undo -> {
            if (canvasStates.size > 1) {
                canvasStates.remove(canvasStates.last())
                canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(canvasStates.last())
            } else if (canvasStates.size == 1 && index != -1) {
                canvasStates.remove(canvasStates.last())
                AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().observe(context, {
                    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(JsonConverter.convertJsonStringToPixelList((it[index!!]).pixelData))
                })
            }
            else {
                if (index == -1) {
                    clearCanvas()
                    canvasStates.clear()
                }
            }
        }
        R.id.fullscreen -> {
            fullscreenEnabled = if (!fullscreenEnabled) {
                enableFullscreen()
                setMenuItemIcon(item, R.drawable.ic_baseline_fullscreen_exit_24, "Exit Fullscreen")
                true
            } else {
                disableFullscreen()
                setMenuItemIcon(item, R.drawable.ic_baseline_fullscreen_24, "Fullscreen")
                false
            }
        }
    }
    return true
}