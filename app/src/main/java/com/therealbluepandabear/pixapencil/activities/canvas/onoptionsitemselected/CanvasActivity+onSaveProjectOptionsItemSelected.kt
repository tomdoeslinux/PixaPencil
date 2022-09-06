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

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawPixelGridViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.getCoverImageBitmap
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.InternalBitmapFileNameGenerator
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.onSaveProjectOptionsItemSelected() {
    viewModel.saved = true

    val coverBitmap = getCoverImageBitmap()
    val coverBitmapFilePath = InternalBitmapFileNameGenerator.generate(projectTitle)

    val fileHelperInstance = FileHelperUtilities.createInstance(this)
    fileHelperInstance.storeBitmapToInternalStorage(coverBitmapFilePath, coverBitmap)

    if (index == -1) {
        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao().insert(
                PixelArt(
                    coverBitmapFilePath,
                    BitmapConverter.convertBitmapToString(drawPixelGridViewBitmap()),
                    drawPixelGridViewBitmap().width,
                    drawPixelGridViewBitmap().height,
                    title.toString(),
                    false
                )
            )
        }
    } else {
        val pixelArt = AppData.pixelArtDB.dao().getAllNoLiveData()[index]
        pixelArt.coverBitmapFilePath = coverBitmapFilePath
        pixelArt.bitmap = BitmapConverter.convertBitmapToString(drawPixelGridViewBitmap())
        pixelArt.width = drawPixelGridViewBitmap().width
        pixelArt.height = drawPixelGridViewBitmap().height

        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao().update(pixelArt)
        }
    }

    onBackPressedDispatcher.onBackPressed()
}