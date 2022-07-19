package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.ColorPalette

fun CanvasActivity.extendedOnColorPaletteTapped(selectedColorPalette: ColorPalette) {
    selectedColorPaletteIndex = AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData().indexOf(selectedColorPalette)
    adapter = ColorPaletteColorPickerAdapter(AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData()[selectedColorPaletteIndex], this)
    binding.activityCanvasColorPickerRecyclerView.adapter = adapter
}