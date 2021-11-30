package com.realtomjoney.pyxlmoose.activities.main

import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.database.ColorDatabase
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter

fun MainActivity.extendedOnResume() {
    binding.activityMainNewProjectButton.show()

    if (!hasNavigatedBack) {
        binding.activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
    } else {
        binding.activityMainRecentCreationsRecyclerView.adapter?.notifyItemInserted(ColorDatabase.toList().last())
    }
}