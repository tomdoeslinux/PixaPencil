package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData


fun MainActivity.observeData(){
    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
        artList.clear()
        artList.addAll(it)
        val currentList =
            when (binding.activityMainBottomNavigationView.selectedItemId) {
                R.id.activityMainBottomNavigationMenu_home_tab -> {
                    artList
                }
                else -> {
                    artList.filter { item -> item.starred }
                }
            }
        adapter.updateDataSource(currentList)
    }
}
 