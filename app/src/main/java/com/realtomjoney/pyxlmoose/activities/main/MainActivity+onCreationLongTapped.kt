package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction

fun MainActivity.extendedOnCreationLongTapped(param: PixelArt) {
    PixelArtDatabase.removeItem(param)
    refreshAdapter()
    binding.recentCreationsRecyclerView.adapter?.notifyItemRemoved(PixelArtDatabase.toList().indexOf((param)))

    (binding.recentCreationsRecyclerView)
        .showSnackbarWithAction("You have deleted ${param.title}", SnackbarDuration.DEFAULT, "Undo") {
            PixelArtDatabase.addItem(param)
            refreshAdapter()
            binding.recentCreationsRecyclerView.adapter?.notifyItemInserted(
                PixelArtDatabase.toList().indexOf((param)))
        }
}