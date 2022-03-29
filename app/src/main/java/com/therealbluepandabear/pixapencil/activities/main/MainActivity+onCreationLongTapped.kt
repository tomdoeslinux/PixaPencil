package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.adapters.RecentCreationsAdapter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MainActivity.extendedOnCreationLongTapped(param: PixelArt) {
    (binding.activityMainRecentCreationsRecyclerView.adapter as RecentCreationsAdapter).userHasLongPressed =
        true

    val paramTitle = param.title

    if ((binding.activityMainRecentCreationsRecyclerView.adapter as RecentCreationsAdapter).userHasLongPressed) {
        showDialog(
            "Delete '$paramTitle'?",
            "Are you sure you want to delete '$paramTitle'? - this cannot be undone.",
            StringConstants.DialogPositiveButtonText, { _, _ ->
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {

                    AppData.pixelArtDB.pixelArtCreationsDao()
                        .deletePixelArtCreation(param.objId)
                    binding.activityMainRecentCreationsRecyclerView.adapter!!.notifyItemRemoved(
                        it.indexOf(
                            param
                        )
                    )
                }
            }, StringConstants.DialogNegativeButtonText, null, null
        )
    }
}