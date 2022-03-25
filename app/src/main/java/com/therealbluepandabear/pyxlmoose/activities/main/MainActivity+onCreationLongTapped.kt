package com.therealbluepandabear.pyxlmoose.activities.main

import com.therealbluepandabear.pyxlmoose.adapters.RecentCreationsAdapter
import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.extensions.showDialog
import com.therealbluepandabear.pyxlmoose.models.PixelArt
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

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
            }, "Cancel", null, null
        )
    }
}