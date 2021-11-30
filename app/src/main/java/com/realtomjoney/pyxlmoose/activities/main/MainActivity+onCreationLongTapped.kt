package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction

fun MainActivity.restoreDeletedItem(param: PixelArt) {
    PixelArtDatabase.addItem(param)
    refreshAdapter()
    binding.activityMainRecentCreationsRecyclerView.adapter?.notifyItemInserted(
        PixelArtDatabase.toList().indexOf((param)))
}

fun MainActivity.extendedOnCreationLongTapped(param: PixelArt) {
    PixelArtDatabase.removeItem(param)
    refreshAdapter()
    binding.activityMainRecentCreationsRecyclerView.adapter?.notifyItemRemoved(PixelArtDatabase.toList().indexOf((param)))

    (binding.activityMainRecentCreationsRecyclerView).showSnackbarWithAction("You have deleted ${param.title}", SnackbarDuration.DEFAULT, "Undo") {
        restoreDeletedItem(param)
    }
}