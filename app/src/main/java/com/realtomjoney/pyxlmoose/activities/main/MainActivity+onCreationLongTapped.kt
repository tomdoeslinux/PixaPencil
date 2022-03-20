package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringConstants

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