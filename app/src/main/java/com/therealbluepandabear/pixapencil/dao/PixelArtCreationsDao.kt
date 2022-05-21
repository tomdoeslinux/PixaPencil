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
    suspend fun deletePixelArtCreation(pixelArtObj: PixelArt)

    @Update
    suspend fun updatePixelArtCreation(pixelArtObj: PixelArt)

    @Query("SELECT * FROM PixelArt WHERE objId =:pixelArtId")
    fun getPixelArtWIthId(pixelArtId:Int) : PixelArt
}