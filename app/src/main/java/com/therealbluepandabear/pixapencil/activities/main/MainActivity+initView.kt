package com.therealbluepandabear.pixapencil.activities.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter


fun MainActivity.initView(){
    binding.apply {
        activityMainRecentCreationsRecyclerView.visibility = View.VISIBLE
        activityMainNewProjectButton.show()
        activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(this@initView, 2)
        adapter = PixelArtCreationsAdapter(binding.clayout, mutableListOf(), this@initView)
        activityMainRecentCreationsRecyclerView.adapter = adapter
    }
}
 
 