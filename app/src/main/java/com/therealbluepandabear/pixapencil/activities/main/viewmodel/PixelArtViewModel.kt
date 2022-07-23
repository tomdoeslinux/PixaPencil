package com.therealbluepandabear.pixapencil.activities.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.repositories.PixelArtRepository

class PixelArtViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PixelArtRepository = PixelArtRepository(application)
    private val all = repository.getAll()

    fun insert(pixelArt: PixelArt) {
        repository.insert(pixelArt)
    }

    fun update(pixelArt: PixelArt) {
        repository.update(pixelArt)
    }

    fun delete(pixelArt: PixelArt) {
        repository.delete(pixelArt)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun getAll(): LiveData<List<PixelArt>> {
        return all
    }
}