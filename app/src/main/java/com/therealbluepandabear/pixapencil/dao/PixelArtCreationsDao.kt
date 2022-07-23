package com.therealbluepandabear.pixapencil.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.therealbluepandabear.pixapencil.models.PixelArt

@Dao
interface PixelArtCreationsDao {
    @Insert
    suspend fun insert(pixelArt: PixelArt)

    @Update
    suspend fun update(pixelArt: PixelArt)

    @Delete
    fun delete(pixelArtObj: PixelArt)

    @Query("SELECT * FROM PixelArt ")
    fun getAll(): LiveData<List<PixelArt>>

    @Query("DELETE FROM PixelArt ")
    fun deleteAll()

    @Query("SELECT * FROM PixelArt ")
    fun getAllNoLiveData(): List<PixelArt>
}