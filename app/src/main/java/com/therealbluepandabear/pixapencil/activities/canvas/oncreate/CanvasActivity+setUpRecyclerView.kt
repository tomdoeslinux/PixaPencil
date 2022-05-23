package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.res.Configuration
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.fromDB
import com.therealbluepandabear.pixapencil.activities.canvas.setPrimaryPixelColor
import com.therealbluepandabear.pixapencil.activities.canvas.setSecondaryPixelColor
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.database.AppData

var firstLoad = false

fun CanvasActivity.setUpRecyclerView() {
    val layoutManager = object : LinearLayoutManager(this) {
        override fun onLayoutCompleted(state: RecyclerView.State) {
            super.onLayoutCompleted(state)
            if (!firstLoad) {
                setPrimaryPixelColor(Color.BLACK)
                setSecondaryPixelColor(Color.BLUE)
                firstLoad = true
            }
        }
    }

    val layoutManagerOrientation: Int = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        LinearLayoutManager.HORIZONTAL
    } else {
        LinearLayoutManager.VERTICAL
    }

    layoutManager.orientation = layoutManagerOrientation
    binding.activityCanvasColorPickerRecyclerView.layoutManager = layoutManager

    val toShow = if (fromDB != null) {
        fromDB
    } else {
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData().firstOrNull()
    }

    toShow?.let {
        adapter = ColorPaletteColorPickerAdapter(it, this@setUpRecyclerView)
    }

    binding.activityCanvasColorPickerRecyclerView.adapter = adapter
}