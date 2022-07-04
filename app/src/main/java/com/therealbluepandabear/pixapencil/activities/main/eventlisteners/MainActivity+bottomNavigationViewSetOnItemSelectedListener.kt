package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun MainActivity.bottomNavigationViewSetOnItemSelectedListener() {
    binding.activityMainBottomNavigationView.setOnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.activityMainBottomNavigationMenu_home_tab -> {
                adapter.submitList(pixelArtData)
            }

            R.id.activityMainBottomNavigationMenu_starred_tab -> {
                adapter.submitList(pixelArtData.filter { pixelArt ->
                    pixelArt.starred
                })
            }
        }
        true
    }
}