package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.app.Activity
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.getCoverImageBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.gridWasEnabled
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.InternalBitmapFileNameGenerator
import com.therealbluepandabear.pixapencil.utility.constants.ObjectConstants
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.onSaveProjectOptionsItemSelected(quietly: Boolean = false) {
    viewModel.saved = true

    val bmp = getCoverImageBitmap()
    val coverBMPFileName = InternalBitmapFileNameGenerator.generate(projectTitle!!)

    val fileHelperInstance = FileHelperUtilities.createInstance(this)
    fileHelperInstance.storeBitmapToInternalStorage(coverBMPFileName, bmp)

    if (index == -1 && !viewModel.createdYet) {
        val pixelArt = PixelArt(
            coverBMPFileName,
            BitmapConverter.convertBitmapToString(outerCanvasInstance.drawPixelGridViewBitmap()),
            width,
            height,
            pixelGridViewInstance.dimenCW,
            pixelGridViewInstance.dimenCH,
            title.toString(),
            false
        )

        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao().insert(pixelArt)
        }

        viewModel.createdYet = true
    } else {
        pixelGridViewInstance.invalidate()

        ObjectConstants.CurrentPixelArtObj.coverBitmapFilePath = coverBMPFileName
        ObjectConstants.CurrentPixelArtObj.bitmap = BitmapConverter.convertBitmapToString(outerCanvasInstance.drawPixelGridViewBitmap())

        CoroutineScope(Dispatchers.IO).launch {
            AppData.pixelArtDB.dao()
                .update(ObjectConstants.CurrentPixelArtObj)
        }
    }

    if (quietly && !ObjectConstants.CurrentPixelArtObjInitialized) {
        AppData.pixelArtDB.dao().getAll().observe(this@onSaveProjectOptionsItemSelected) {
            ObjectConstants.CurrentPixelArtObj = it.last()

//            if (ObjectConstants.CurrentPixelArtObj.objId == AppData.pixelArtDB.dao()
//                    .getAllPixelArtCreationsNoLiveData().last().objId
//            ) {
//                Toast.makeText(
//                    this@onSaveProjectOptionsItemSelected,
//                    getString(R.string.generic_saved_in_code_str),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        }
    }

    if (!quietly) {
        (this as Activity).onBackPressed()
    } else if (quietly && gridWasEnabled) {
        pixelGridViewInstance.gridEnabled = true
    }
}