package com.therealbluepandabear.pixapencil.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.models.PixelArt

@Dao
interface ColorPalettesDao {
    @Insert
    suspend fun insertColorPalette(colorPalette: ColorPalette)

    @Query("SELECT * FROM ColorPalette")
    fun getAllColorPalettes(): LiveData<List<ColorPalette>>

    @Query("SELECT * FROM ColorPalette")
    fun getAllColorPalettesNoLiveData(): List<ColorPalette>

    @Delete
    fun deleteColorPalette(colorPaletteObj: ColorPalette)

    @Update
    fun updateColorPalette(colorPaletteObj: ColorPalette)
}