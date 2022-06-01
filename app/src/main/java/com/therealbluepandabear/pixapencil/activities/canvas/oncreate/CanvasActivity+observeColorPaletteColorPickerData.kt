package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.AppData

fun CanvasActivity.observeColorPaletteColorPickerData() {
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this@observeColorPaletteColorPickerData) {
        adapter.updateData(it[selectedColorPaletteIndex])
    }
}
 