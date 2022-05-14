package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.fromDB
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.database.AppData

fun CanvasActivity.setUpRecyclerView() {
    val layoutManager = LinearLayoutManager(this)

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