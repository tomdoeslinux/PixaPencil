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

package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.widget.Toast
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawdrawingViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.getCoverImageBitmap
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.InternalBitmapFileNameGenerator
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.onSaveInBackgroundOptionsItemSelected() {
    val coverBitmap = getCoverImageBitmap()
    val coverBitmapFilePath = InternalBitmapFileNameGenerator.generate(projectTitle)

    val fileHelperInstance = FileHelperUtilities.createInstance(this)
    fileHelperInstance.storeBitmapToInternalStorage(coverBitmapFilePath, coverBitmap)

    if (index == -1) {
        val pixelArt = PixelArt(
            coverBitmapFilePath,
            BitmapConverter.convertBitmapToString(drawdrawingViewBitmap()),
            width,
            height,
            title.toString(),
            false
        )
        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao().insert(pixelArt)
        }

        AppData.pixelArtDB.dao().getAll().observe(this) {
            index = it.indexOf(pixelArt)
        }
    } else {
        val pixelArt = AppData.pixelArtDB.dao().getAllNoLiveData()[index]
        pixelArt.coverBitmapFilePath = coverBitmapFilePath
        pixelArt.bitmap = BitmapConverter.convertBitmapToString(drawdrawingViewBitmap())

        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao().update(pixelArt)
        }
    }

    viewModel.saved = true
    Toast.makeText(this, getString(R.string.generic_saved), Toast.LENGTH_LONG).show()
}