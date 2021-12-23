package com.realtomjoney.pyxlmoose.activities.main

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.ColorPalette

fun MainActivity.extendedOnResume() {
    binding.apply {
        activityMainRecentCreationsRecyclerView.visibility = View.VISIBLE
        activityMainNewProjectButton.show()
        activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this, {
        binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(it, this)
    })
}