package com.realtomjoney.pyxlmoose.activities.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.AppData

fun MainActivity.extendedOnResume() {
    binding.apply {
        activityMainRecentCreationsRecyclerView.visibility = View.VISIBLE
        activityMainNewProjectButton.show()
        activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().observe(this, {
        binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(it, this)
    })
}