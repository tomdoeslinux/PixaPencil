/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.google.android.material.tabs.TabLayout
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.ViewPagerAdapter

fun CanvasActivity.setupViewPager() {
    binding.activityCanvasViewPager2.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
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
