package com.therealbluepandabear.pixapencil.fragments.colorpicker

import android.view.View
import com.google.android.material.tabs.TabLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.picker.ColorPickerPickerFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb.RGBColorPickerFragment

fun ColorPickerFragment.setOnClickListeners() {
    hexFragmentInstance = HexadecimalColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, hexFragmentInstance!!).commit()

    rgbFragmentInstance = RGBColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, rgbFragmentInstance!!).commit()

    pickerFragmentInstance = ColorPickerPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, pickerFragmentInstance!!).commit()

    binding.fragmentColorPickerTabLayout.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            when (tab.text) {
                getString(R.string.fragmentColorPicker_tab_color_picker_str) -> {
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.fragmentColorPicker_tab_rgb_str) -> {
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.fragmentColorPicker_tab_hex_str) -> {
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}