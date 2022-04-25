package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnCreationLongTapped(pixelArtObj: PixelArt) {
    (binding.activityMainRecentCreationsRecyclerView.adapter as PixelArtCreationsAdapter).userHasLongPressed =
        true

    val title = pixelArtObj.title

    if ((binding.activityMainRecentCreationsRecyclerView.adapter as PixelArtCreationsAdapter).userHasLongPressed) {
        showDialog(
            getString(R.string.dialog_delete_pixel_art_project_title_in_code_str, title),
            getString(R.string.dialog_delete_pixel_art_project_text_in_code_str, title),
            getString(R.string.dialog_positive_button_text_in_code_str), { _, _ ->
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {

                    AppData.pixelArtDB.pixelArtCreationsDao()
                        .deletePixelArtCreation(pixelArtObj.objId)
                    binding.activityMainRecentCreationsRecyclerView.adapter!!.notifyItemRemoved(
                        it.indexOf(
                            pixelArtObj
                        )
                    )
                }
            },  getString(R.string.dialog_negative_button_text_in_code_str), null, null
        )
    }
}