package com.realtomjoney.pyxlmoose.activities.main

import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.database.ColourDatabase
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter

fun MainActivity.extendedOnResume() {
    binding.floatingActionButton.show()

    if (!hasNavigatedBack) {
        binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
    } else {
        binding.recentCreationsRecyclerView.adapter?.notifyItemInserted(ColourDatabase.toList().last())
    }
}