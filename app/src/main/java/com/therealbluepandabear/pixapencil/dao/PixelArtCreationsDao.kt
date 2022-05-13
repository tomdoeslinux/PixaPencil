package com.therealbluepandabear.pixapencil.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Update
    fun updatePixelArtCreation(pixelArtObj: PixelArt)
}