package com.therealbluepandabear.pyxlmoose.activities.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.therealbluepandabear.pyxlmoose.adapters.RecentCreationsAdapter
import com.therealbluepandabear.pyxlmoose.database.AppData

fun MainActivity.extendedOnResume() {
    binding.apply {
        activityMainRecentCreationsRecyclerView.visibility = View.VISIBLE
        activityMainNewProjectButton.show()
        activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
        binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(it, this)
    }
}