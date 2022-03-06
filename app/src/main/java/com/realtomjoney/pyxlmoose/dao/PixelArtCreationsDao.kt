package com.realtomjoney.pyxlmoose.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.realtomjoney.pyxlmoose.models.PixelArt

@Dao
interface PixelArtCreationsDao {
    @Insert
    suspend fun insertPixelArt(pixelArt: PixelArt)

    @Query("SELECT * FROM PixelArt ")
    fun getAllPixelArtCreations(): LiveData<List<PixelArt>>

    @Query("SELECT * FROM PixelArt ")
    fun getAllPixelArtCreationsNoLiveData(): List<PixelArt>

    @Query("DELETE FROM PixelArt WHERE objId=:pixelArtId")
    fun deletePixelArtCreation(pixelArtId: Int)

    @Query("UPDATE PixelArt SET item_cover_bitmap=:coverBitmap WHERE objId=:id_t")
    fun updatePixelArtCreationCoverBitmap(coverBitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_bitmap=:bitmap WHERE objId=:id_t")
    fun updatePixelArtCreationBitmap(bitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_starred=:starred WHERE objId=:id_t")
    fun updatePixelArtCreationStarred(starred: Boolean, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_rotation=:rotation WHERE objId=:id_t")
    fun updatePixelArtCreationRotation(rotation: Float, id_t: Int): Int
}