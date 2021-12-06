package com.realtomjoney.pyxlmoose.activities.main

import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.NewCanvasFragment


fun MainActivity.extendedSetOnClickListeners() {
    binding.bottomNavigationView.setOnItemSelectedListener { item ->
        when(item.itemId) {
            R.id.page_home -> {
//                binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(
//                    AppData.db.pixelArtCreationsDao().getAllPixelArtCreations(), this)
                title = "Home"
            }
            R.id.page_starred -> {
//                binding.activityMainRecentCreationsRecyclerView.adapter = RecentCreationsAdapter(
//                    AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().filter { it.favourited }, this)
                title = "Favorites"
            }
        }
        true
    }

    binding.activityMainRecentCreationsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy == 1 || dy == 0) return

            if (binding.activityMainNewProjectButton.isShown && dy > 2 || dy < 2) binding.activityMainNewProjectButton.hide() else binding.activityMainNewProjectButton.show()
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) binding.activityMainNewProjectButton.show()
        }
    })

    binding.activityMainNewProjectButton.setOnClickListener {
        newCanvasFragmentInstance = NewCanvasFragment.newInstance()
        navigateTo(supportFragmentManager, newCanvasFragmentInstance, R.id.newCanvasFragmentHost, "New Canvas", binding.newCanvasFragmentHost, binding.mainRoot)
    }
}