package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.activities.main.binding
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter
import com.therealbluepandabear.pixapencil.database.AppData

fun MainActivity.bottomNavigationViewSetOnItemSelectedListener() {
    binding.activityMainBottomNavigationView.setOnItemSelectedListener { item ->
        when(item.itemId) {
            R.id.activityMainBottomNavigationMenu_home_tab -> {
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
                    binding.activityMainRecentCreationsRecyclerView.adapter =
                        PixelArtCreationsAdapter(binding.clayout, it, this)
                }
            }
            R.id.activityMainBottomNavigationMenu_starred_tab -> {
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
                    binding.activityMainRecentCreationsRecyclerView.adapter =
                        PixelArtCreationsAdapter(binding.clayout, it.filter { item -> item.starred }, this)
                }
            }
        }
        true
    }
}