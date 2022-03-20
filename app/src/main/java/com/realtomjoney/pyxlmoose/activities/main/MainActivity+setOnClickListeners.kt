package com.realtomjoney.pyxlmoose.activities.main

import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.newcanvas.NewCanvasFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants


fun MainActivity.setOnClickListeners() {
    binding.activityMainBottomNavigationView.setOnItemSelectedListener { item ->
        when(item.itemId) {
            R.id.page_home -> {
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
                    binding.activityMainRecentCreationsRecyclerView.adapter =
                        RecentCreationsAdapter(it, this)
                }
                title = StringConstants.AppName
            }
            R.id.page_starred -> {
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
                    binding.activityMainRecentCreationsRecyclerView.adapter =
                        RecentCreationsAdapter(it.filter { item -> item.starred }, this)
                }
                title = StringConstants.TabStarredTitle
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
        currentFragmentInstance = newCanvasFragmentInstance
        navigateTo(supportFragmentManager, newCanvasFragmentInstance, R.id.activityMain_primaryFragmentHost, "New Canvas", binding.activityMainPrimaryFragmentHost, binding.mainRoot)
    }
}