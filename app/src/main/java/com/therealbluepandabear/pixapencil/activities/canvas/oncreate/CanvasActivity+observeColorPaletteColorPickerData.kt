package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.AppData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.observeColorPaletteColorPickerData() {
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this@observeColorPaletteColorPickerData) {
        adapter.updateData(it.first())
    }
}
 