package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt

//fun MainActivity.restoreDeletedItem(param: PixelArts) {
//    CoroutineScope(Dispatchers.IO).launch {
//        AppData.db.pixelArtCreationsDao().insertPixelArt(param)
//    }
//
//    AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().observe(this, {
//         binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(it, this)
//    })
//
//    (binding.activityMainRecentCreationsRecyclerView.adapter as RecentCreationsAdapter).userHasLongPressed = false
//
//}

fun MainActivity.extendedOnCreationLongTapped(param: PixelArt) {
    (binding.activityMainRecentCreationsRecyclerView.adapter as RecentCreationsAdapter).userHasLongPressed = true

    AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().observe(this, {
        AppData.db.pixelArtCreationsDao().deletePixelArtCreation(param.objId)
        binding.activityMainRecentCreationsRecyclerView.adapter!!.notifyItemRemoved(it.indexOf(param))
     })

//
//    (binding.activityMainRecentCreationsRecyclerView).showSnackbarWithAction("You have deleted ${param.title}", SnackbarDuration.DEFAULT, "Undo") {
//        restoreDeletedItem(param)
//    }
}