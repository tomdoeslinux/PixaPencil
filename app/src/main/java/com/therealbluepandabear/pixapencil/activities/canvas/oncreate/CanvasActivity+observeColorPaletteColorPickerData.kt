package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.database.AppData

fun CanvasActivity.observeColorPaletteColorPickerData() {
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this@observeColorPaletteColorPickerData) {
        if (selectedColorPaletteIndex <= it.size) {
            adapter.updateData(it[selectedColorPaletteIndex])
        }
    }
}
 