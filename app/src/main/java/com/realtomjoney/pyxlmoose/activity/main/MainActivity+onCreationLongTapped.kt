package com.realtomjoney.pyxlmoose.activity.main

import com.realtomjoney.pyxlmoose.PixelArt
import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.SnackbarDuration
import com.realtomjoney.pyxlmoose.showSnackbarWithAction

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