package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnCreationLongTapped(param: PixelArt) {
    (binding.activityMainRecentCreationsRecyclerView.adapter as PixelArtCreationsAdapter).userHasLongPressed =
        true

    val paramTitle = param.title

    if ((binding.activityMainRecentCreationsRecyclerView.adapter as PixelArtCreationsAdapter).userHasLongPressed) {
        showDialog(
            "Delete '$paramTitle'?",
            "Are you sure you want to delete '$paramTitle'? - this cannot be undone.",
            getString(R.string.dialog_positive_button_text_in_code_str), { _, _ ->
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {

                    AppData.pixelArtDB.pixelArtCreationsDao()
                        .deletePixelArtCreation(param.objId)
                    binding.activityMainRecentCreationsRecyclerView.adapter!!.notifyItemRemoved(
                        it.indexOf(
                            param
                        )
                    )
                }
            },  getString(R.string.dialog_negative_button_text_in_code_str), null, null
        )
    }
}