package com.realtomjoney.pyxlmoose.activity.main

import android.app.Activity
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.ColourDatabase
import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.RecentCreationsAdapter

fun MainActivity.extendedOnResume() {
    binding.floatingActionButton.show()

    if (!hasNavigatedBack) {
        binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
    } else {
        binding.recentCreationsRecyclerView.adapter?.notifyItemInserted(ColourDatabase.toList().last())
    }
}