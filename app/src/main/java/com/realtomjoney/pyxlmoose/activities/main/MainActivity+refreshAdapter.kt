package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter

fun MainActivity.extendedRefreshAdapter() {
    binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
}