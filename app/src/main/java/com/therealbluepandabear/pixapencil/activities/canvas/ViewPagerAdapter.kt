package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ToolsFragment.newInstance()
            }

            1 -> {
                FiltersFragment.newInstance()
            }

            2 -> {
                ColorPalettesFragment.newInstance()
            }

            3 -> {
                BrushesFragment.newInstance()
            }

            else -> {
                ToolsFragment.newInstance()
            }
        }
    }
}