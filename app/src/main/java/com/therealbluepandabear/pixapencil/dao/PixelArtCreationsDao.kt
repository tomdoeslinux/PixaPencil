package com.therealbluepandabear.pixapencil.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.therealbluepandabear.pixapencil.models.PixelArt

@Dao
interface PixelArtCreationsDao {
    @Insert
    suspend fun insertPixelArt(pixelArt: PixelArt)

    @Query("SELECT * FROM PixelArt ")
    fun getAllPixelArtCreations(): LiveData<List<PixelArt>>

    @Query("SELECT * FROM PixelArt ")
    fun getAllPixelArtCreationsNoLiveData(): List<PixelArt>

    @Delete
    fun deletePixelArtCreation(pixelArtObj: PixelArt)

    @Query("UPDATE PixelArt SET item_cover_bitmap_file_path=:coverBitmapFilePath WHERE objId=:id_t")
    fun updatePixelArtCreationCoverBitmap(coverBitmapFilePath: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_bitmap=:bitmap WHERE objId=:id_t")
    fun updatePixelArtCreationBitmap(bitmap: String, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_starred=:starred WHERE objId=:id_t")
    fun updatePixelArtCreationStarred(starred: Boolean, id_t: Int): Int

    @Query("UPDATE PixelArt SET item_rotation=:rotation WHERE objId=:id_t")
    fun updatePixelArtCreationRotation(rotation: Float, id_t: Int): Int
}