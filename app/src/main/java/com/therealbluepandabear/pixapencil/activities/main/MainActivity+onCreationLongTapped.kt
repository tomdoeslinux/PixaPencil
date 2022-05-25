package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MainActivity.extendedOnCreationLongTapped(pixelArtObj: PixelArt) {
    val title = pixelArtObj.title

    showDialog(
        getString(R.string.dialog_delete_pixel_art_project_title_in_code_str, title),
        getString(R.string.dialog_delete_pixel_art_project_text_in_code_str, title),
        getString(R.string.dialog_delete_pixel_art_project_positive_button_text_in_code_str), { _, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                AppData.pixelArtDB.pixelArtCreationsDao().deletePixelArtCreation(pixelArtObj)
            }
        },  getString(R.string.dialog_delete_pixel_art_project_negative_button_text_in_code_str), null
    )
}