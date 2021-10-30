package com.realtomjoney.pyxlmoose.activity.main

import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.RecentCreationsAdapter

fun MainActivity.extendedRefreshAdapter() {
    binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
}