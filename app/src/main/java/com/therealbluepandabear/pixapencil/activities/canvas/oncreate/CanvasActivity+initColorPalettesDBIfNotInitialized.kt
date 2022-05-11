package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.database.ColorPalettesDatabase

fun CanvasActivity.initColorPalettesDBIfNotInitialized() {
    if (!AppData.colorPalettesDBInitialized) {
        AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
    }
}