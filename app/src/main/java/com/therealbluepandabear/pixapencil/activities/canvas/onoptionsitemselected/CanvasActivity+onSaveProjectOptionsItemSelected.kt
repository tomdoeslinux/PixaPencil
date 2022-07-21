package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.app.Activity
import android.util.Log
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
    val coverBitmapFilePath = InternalBitmapFileNameGenerator.generate(projectTitle!!)

    val fileHelperInstance = FileHelperUtilities.createInstance(this)
    fileHelperInstance.storeBitmapToInternalStorage(coverBitmapFilePath, coverBitmap)

    Log.d("ABCDEFG", "${drawPixelGridViewBitmap().width} ${drawPixelGridViewBitmap().height}")

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

    (this as Activity).onBackPressed()
}