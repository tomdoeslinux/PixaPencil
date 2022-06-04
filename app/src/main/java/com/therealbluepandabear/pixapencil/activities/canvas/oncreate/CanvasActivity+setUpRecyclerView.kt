package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.setPrimaryPixelColor
import com.therealbluepandabear.pixapencil.activities.canvas.setSecondaryPixelColor
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.models.ColorPalette

var firstLoad = false

fun CanvasActivity.setUpRecyclerView() {
    val layoutManager = object : LinearLayoutManager(this) {
        override fun onLayoutCompleted(state: RecyclerView.State) {
            super.onLayoutCompleted(state)
            if (!firstLoad) {
                setPrimaryPixelColor(viewModel.primaryColor)
                setSecondaryPixelColor(viewModel.secondaryColor)
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

    adapter = ColorPaletteColorPickerAdapter(ColorPalette(null, JsonConverter.convertListToJsonString(listOf<String>())), this@setUpRecyclerView)
    binding.activityCanvasColorPickerRecyclerView.adapter = adapter
}