package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.models.ColorPalette

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