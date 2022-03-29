package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.ColorPalette

var fromDB: ColorPalette? = null

fun CanvasActivity.extendedOnAddColorTapped(colorPalette: ColorPalette) {
    hideMenuItems()

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        for (data in it) {
            if (data.objId == colorPalette.objId) {
                fromDB = data
                break
            }
        }
    }
    openColorPickerDialog(true)
}