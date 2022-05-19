package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.activities.main.binding

fun MainActivity.bottomNavigationViewSetOnItemSelectedListener() {
    binding.activityMainBottomNavigationView.setOnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.activityMainBottomNavigationMenu_home_tab -> {
                adapter.updateDataSource(artList)
            }
            R.id.activityMainBottomNavigationMenu_starred_tab -> {
                adapter.updateDataSource(artList.filter { pixelArt -> pixelArt.starred })
            }
        }
        true
    }
}