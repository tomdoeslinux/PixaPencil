package com.therealbluepandabear.pixapencil.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.therealbluepandabear.pixapencil.dao.PixelArtCreationsDao
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.database.PixelArtDatabase
import com.therealbluepandabear.pixapencil.models.PixelArt
import kotlinx.coroutines.*

class PixelArtRepository(application: Application) {
    init {
        AppData.pixelArtDB = PixelArtDatabase.get(application)
    }

    private val dao: PixelArtCreationsDao = AppData.pixelArtDB.dao()
    private val allCreations: LiveData<List<PixelArt>> = AppData.pixelArtDB.dao().getAll()

    fun insert(pixelArt: PixelArt) {
        InsertAsync(dao).execute(pixelArt)
    }

    fun update(pixelArt: PixelArt) {
        UpdateAsync(dao).execute(pixelArt)
    }

    fun delete(pixelArt: PixelArt) {
        DeleteAsync(dao).execute(pixelArt)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun getAll(): LiveData<List<PixelArt>> {
        return allCreations
    }

    private class InsertAsync(private val dao: PixelArtCreationsDao) : CoroutineScope by MainScope() {
        fun execute(pixelArt: PixelArt) {
            CoroutineScope(Dispatchers.IO).launch {
                dao.insert(pixelArt)
            }
        }
    }

    private class UpdateAsync(private val dao: PixelArtCreationsDao) : CoroutineScope by MainScope() {
        fun execute(pixelArt: PixelArt) {
            CoroutineScope(Dispatchers.IO).launch {
                dao.update(pixelArt)
            }
        }
    }

    private class DeleteAsync(private val dao: PixelArtCreationsDao) : CoroutineScope by MainScope() {
        fun execute(pixelArt: PixelArt) {
            CoroutineScope(Dispatchers.IO).launch {
                dao.delete(pixelArt)
            }
        }
    }
}