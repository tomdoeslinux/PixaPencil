package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.hideItems
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.models.ColorPalette

var fromDB: ColorPalette? = null

fun CanvasActivity.extendedOnAddColorTapped(colorPalette: ColorPalette) {
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        for (data in it) {
            if (data.objId == colorPalette.objId) {
                fromDB = data
                break
            }
        }
    }
    supportFragmentManager.commit {
        replace(
            R.id.activityCanvas_primaryFragmentHost, ColorPickerFragment.newInstance(
            paramOldColor = getSelectedColor(),
            paramColorPaletteMode = true))
        addToBackStack(null)
    }
}