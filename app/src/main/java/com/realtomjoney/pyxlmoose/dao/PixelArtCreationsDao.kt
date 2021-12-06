package com.realtomjoney.pyxlmoose.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.realtomjoney.pyxlmoose.models.*

@Dao
interface PixelArtCreationsDao {
    @Insert
    suspend fun insertPixelArt(pixelArt: PixelArt)

    @Query("SELECT * FROM PixelArt ")
    fun getAllPixelArtCreations(): LiveData<List<PixelArt>>

    @Query("DELETE FROM PixelArt WHERE objId=:pixelArtId")
    fun deletePixelArtCreation(pixelArtId: Int)

    @Query("UPDATE PixelArt SET item_bitmap=:bitmap WHERE objId=:id_t")
    fun updatePixelArtCreationBitmap(bitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_pixel_data=:pixelData WHERE objId=:id_t")
    fun updatePixelArtCreationPixelData(pixelData: String, id_t: Int): Int
}