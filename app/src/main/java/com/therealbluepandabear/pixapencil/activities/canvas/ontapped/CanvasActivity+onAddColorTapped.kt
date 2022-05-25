package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import android.graphics.Color
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.models.ColorPalette

var fromDB: ColorPalette? = null

fun CanvasActivity.extendedOnAddColorTapped(colorPalette: ColorPalette) {
        for (data in AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData()) {
            if (data.objId == colorPalette.objId) {
                fromDB = data
                break
            }
        }

    supportFragmentManager.commit {
        replace(
            R.id.activityCanvas_primaryFragmentHost, ColorPickerFragment.newInstance(
            paramOldColor = Color.WHITE,
            paramColorPaletteMode = true))
        addToBackStack(null)
    }
}