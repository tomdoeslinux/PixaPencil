package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.models.ColorPalette

fun CanvasActivity.setupRecyclerView() {
    adapter = ColorPaletteColorPickerAdapter(ColorPalette(null, JsonConverter.convertListToJsonString(listOf<String>())), this@setupRecyclerView)
    binding.activityCanvasColorPickerRecyclerView.adapter = adapter
}