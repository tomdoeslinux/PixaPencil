package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.google.android.material.tabs.TabLayout
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.ViewPagerAdapter
import com.therealbluepandabear.pixapencil.activities.canvas.binding

fun CanvasActivity.setupViewPager() {
    val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
    binding.activityCanvasViewPager2.adapter = adapter
    binding.activityCanvasViewPager2.offscreenPageLimit = 3
    binding.activityCanvasViewPager2.isUserInputEnabled = false
    binding.activityCanvasTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            binding.activityCanvasViewPager2.setCurrentItem(tab.position, false)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) { }

        override fun onTabReselected(tab: TabLayout.Tab?) { }
    })
}
