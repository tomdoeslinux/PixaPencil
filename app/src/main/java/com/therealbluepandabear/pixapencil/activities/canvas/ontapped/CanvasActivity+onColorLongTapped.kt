/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.converters.JSON
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
    val colorData = JSON.stringToIntList(colorPalette.colorPaletteColorData).toMutableList()
    val colorToRemove = colorData[colorIndex]
    colorData.removeAt(colorIndex)
    colorPalette.colorPaletteColorData = JSON.listToString(colorData)

    CoroutineScope(Dispatchers.IO).launch {
        AppData.colorPalettesDB.colorPalettesDao().updateColorPalette(colorPalette)
    }

    binding.activityCanvasCoordinatorLayout.showSnackbarWithAction(getString(R.string.snackbar_on_color_long_tapped, colorPalette.colorPaletteName), SnackbarDuration.Default, getString(R.string.activityCanvasTopAppMenu_undo)) {
        colorData.add(colorIndex, colorToRemove)
        colorPalette.colorPaletteColorData = JSON.listToString(colorData)

        CoroutineScope(Dispatchers.IO).launch {
            AppData.colorPalettesDB.colorPalettesDao().updateColorPalette(colorPalette)
        }
    }
}