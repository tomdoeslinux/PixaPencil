package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData

fun CanvasActivity.observeColorPaletteColorPickerData() {
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        colorPaletteColorPickerData.clear()
        colorPaletteColorPickerData.addAll(JsonConverter.convertJsonStringToListOfInt(it[selectedColorPaletteIndex].colorPaletteColorData))

        adapter.updateDataSource(colorPaletteColorPickerData)
    }
}
 