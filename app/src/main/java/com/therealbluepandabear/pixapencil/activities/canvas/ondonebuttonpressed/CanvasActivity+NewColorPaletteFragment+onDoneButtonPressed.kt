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

package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.getColors
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var palette: ColorPalette

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
    supportFragmentManager.popBackStackImmediate()

    CoroutineScope(Dispatchers.IO).launch {
        if (!extractColorPaletteFromCanvas) {
            palette = ColorPalette(colorPaletteTitle, JsonConverter.convertListToJsonString(listOf(Color.TRANSPARENT)))
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(palette)
        } else {
            val data = binding.activityCanvasPixelGridView.pixelGridViewBitmap.getColors().toMutableList()
            data.add(Color.TRANSPARENT)

            palette = ColorPalette(colorPaletteTitle, JsonConverter.convertListToJsonString(data))
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(palette)
        }
    }

    binding.root.post {
        judgeUndoRedoStacks()
        onColorPaletteTapped(palette)
    }
}