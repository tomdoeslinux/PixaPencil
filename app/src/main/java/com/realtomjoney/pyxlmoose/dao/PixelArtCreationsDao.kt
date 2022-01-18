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

    @Query("UPDATE PixelArt SET item_cover_bitmap=:coverBitmap WHERE objId=:id_t")
    fun updatePixelArtCreationCoverBitmap(coverBitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_bitmap=:bitmap WHERE objId=:id_t")
    fun updatePixelArtCreationBitmap(bitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_favourited=:favorited WHERE objId=:id_t")
    fun updatePixelArtCreationFavorited(favorited: Boolean, id_t: Int): Int
}