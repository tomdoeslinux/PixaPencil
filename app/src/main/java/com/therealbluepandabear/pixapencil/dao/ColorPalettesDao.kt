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

package com.therealbluepandabear.pixapencil.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.therealbluepandabear.pixapencil.models.ColorPalette

@Dao
interface ColorPalettesDao {
    @Insert
    suspend fun insertColorPalette(colorPalette: ColorPalette)

    @Query("SELECT * FROM ColorPalette")
    fun getAllColorPalettes(): LiveData<List<ColorPalette>>

    @Query("SELECT * FROM ColorPalette")
    fun getAllColorPalettesNoLiveData(): List<ColorPalette>

    @Delete
    suspend fun deleteColorPalette(colorPaletteObj: ColorPalette)

    @Update
    suspend fun updateColorPalette(colorPaletteObj: ColorPalette)
}