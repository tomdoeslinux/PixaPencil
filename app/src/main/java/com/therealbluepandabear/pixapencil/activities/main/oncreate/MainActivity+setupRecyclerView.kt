package com.therealbluepandabear.pixapencil.activities.main.oncreate

import androidx.recyclerview.widget.GridLayoutManager
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.adapters.PixelArtAdapter


fun MainActivity.setupRecyclerView() {
    binding.activityMainRecentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
    binding.activityMainRecentCreationsRecyclerView.setHasFixedSize(true)

    adapter = PixelArtAdapter(binding.clayout, this, this)
    binding.activityMainRecentCreationsRecyclerView.adapter = adapter
}
 
 