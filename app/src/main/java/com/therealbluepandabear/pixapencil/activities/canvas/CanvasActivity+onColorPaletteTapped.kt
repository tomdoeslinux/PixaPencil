package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.ColorPalette

fun CanvasActivity.extendedOnColorPaletteTapped(selectedColorPalette: ColorPalette) {
    binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(selectedColorPalette, this)

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) { it ->
        selectedColorPaletteIndex = it.indexOf(it.first { it.objId == selectedColorPalette.objId })
    }
}