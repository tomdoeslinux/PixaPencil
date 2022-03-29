package com.therealbluepandabear.pixapencil.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.therealbluepandabear.pixapencil.models.ColorPalette

@Dao
interface ColorPalettesDao {
    @Insert
    suspend fun insertColorPalette(colorPalette: ColorPalette)

    @Query("SELECT * FROM ColorPalette")
    fun getAllColorPalettes(): LiveData<List<ColorPalette>>

    @Query("DELETE FROM ColorPalette WHERE objId=:colorPaletteId")
    fun deleteColorPalette(colorPaletteId: Int)

    @Query("UPDATE ColorPalette SET item_color_palette_color_data=:colorData WHERE objId=:id_t")
    fun updateColorPaletteColorData(colorData: String, id_t: Int)
}