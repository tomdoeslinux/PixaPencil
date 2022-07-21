package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.widget.Toast
import com.therealbluepandabear.pixapencil.R
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

fun CanvasActivity.onSaveInBackgroundOptionsItemSelected() {
    val coverBitmap = getCoverImageBitmap()
    val coverBitmapFilePath = InternalBitmapFileNameGenerator.generate(projectTitle!!)

    val fileHelperInstance = FileHelperUtilities.createInstance(this)
    fileHelperInstance.storeBitmapToInternalStorage(coverBitmapFilePath, coverBitmap)

    if (index == -1) {
        val pixelArt = PixelArt(
            coverBitmapFilePath,
            BitmapConverter.convertBitmapToString(drawPixelGridViewBitmap()),
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
        pixelArt.bitmap = BitmapConverter.convertBitmapToString(drawPixelGridViewBitmap())

        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao().update(pixelArt)
        }
    }

    viewModel.saved = true
    Toast.makeText(this, getString(R.string.generic_saved_in_code_str), Toast.LENGTH_LONG).show()
}