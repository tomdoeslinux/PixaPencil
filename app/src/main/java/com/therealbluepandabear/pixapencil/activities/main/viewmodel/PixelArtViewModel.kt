/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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