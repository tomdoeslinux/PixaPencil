package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt

fun MainActivity.extendedOnCreationLongTapped(param: PixelArt) {
    (binding.activityMainRecentCreationsRecyclerView.adapter as RecentCreationsAdapter).userHasLongPressed = true

    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
        AppData.pixelArtDB.pixelArtCreationsDao().deletePixelArtCreation(param.objId)
        binding.activityMainRecentCreationsRecyclerView.adapter!!.notifyItemRemoved(it.indexOf(param))
    }
}