package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pyxlmoose.adapters.ColorPickerAdapter
import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.extensions.getScreenOrientation

const val DEF_SPAN_COUNT = 1

fun CanvasActivity.setUpRecyclerView() {
    val layoutManager = GridLayoutManager(this, DEF_SPAN_COUNT)

    val layoutManagerOrientation: Int = if (getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
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