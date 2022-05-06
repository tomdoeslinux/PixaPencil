package com.therealbluepandabear.pixapencil.activities.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter
import com.therealbluepandabear.pixapencil.database.AppData

fun MainActivity.extendedOnResume() {
    binding.apply {
        activityMainRecentCreationsRecyclerView.visibility = View.VISIBLE
        activityMainNewProjectButton.show()
        activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(this@extendedOnResume, 2)
    }

    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
        binding.activityMainRecentCreationsRecyclerView.adapter = PixelArtCreationsAdapter(binding.clayout, it, this)
    }
}