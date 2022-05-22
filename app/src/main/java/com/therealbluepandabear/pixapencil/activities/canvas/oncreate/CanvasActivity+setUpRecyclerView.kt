package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.res.Configuration
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.fromDB
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
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

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        val toShow = if (fromDB != null) {
            fromDB
        } else {
            it.firstOrNull()
        }
        binding.activityCanvasColorPickerRecyclerView.adapter =
            toShow?.let { obj ->
                ColorPickerAdapter(obj, this)
            }
    }
}