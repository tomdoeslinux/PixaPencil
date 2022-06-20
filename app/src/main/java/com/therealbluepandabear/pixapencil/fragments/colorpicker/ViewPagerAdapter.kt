package com.therealbluepandabear.pixapencil.fragments.colorpicker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.therealbluepandabear.pixapencil.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.picker.ColorPickerPickerFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb.RGBColorPickerFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ColorPickerPickerFragment.newInstance()
            }

            1 -> {
                RGBColorPickerFragment.newInstance()
            }

            2 -> {
                HexadecimalColorPickerFragment.newInstance()
            }

            else -> {
                ColorPickerPickerFragment.newInstance()
            }
        }
    }
}