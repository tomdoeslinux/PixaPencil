package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData


fun MainActivity.observeData(){
    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
        pixelArtList.clear()
        pixelArtList.addAll(it)

        val currentList =
            when (binding.activityMainBottomNavigationView.selectedItemId) {
                R.id.activityMainBottomNavigationMenu_home_tab -> {
                    pixelArtList
                }

                else -> {
                    pixelArtList.filter { item ->
                        item.starred
                    }
                }
            }

        adapter.updateDataSource(currentList)
    }
}
 