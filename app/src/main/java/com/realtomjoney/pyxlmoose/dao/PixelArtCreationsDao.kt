package com.realtomjoney.pyxlmoose.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.realtomjoney.pyxlmoose.models.*

@Dao
interface PixelArtCreationsDao {
    @Insert
    suspend fun insertPixelArt(pixelArt: PixelArts)

    @Query("SELECT * FROM PixelArts ")
    fun getAllPixelArtCreations(): LiveData<List<PixelArts>>

    @Query("DELETE FROM PixelArts WHERE objId=:pixelArtId")
    fun deletePixelArtCreation(pixelArtId: Int)

    @Query("UPDATE PixelArts SET item_bitmap=:bitmap WHERE objId=:id_t")
    fun updatePixelArtCreationBitmap(bitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArts SET item_pixel_data=:pixelData WHERE objId=:id_t")
    fun updatePixelArtCreationPixelData(pixelData: String, id_t: Int): Int
}