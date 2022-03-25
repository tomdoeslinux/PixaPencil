package com.therealbluepandabear.pyxlmoose.fragments.colorpicker

import android.view.View
import com.google.android.material.tabs.TabLayout
import com.therealbluepandabear.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hsv.HSVColorPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.picker.ColorPickerPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.rgb.RGBColorPickerFragment

fun ColorPickerFragment.setOnClickListeners() {
    hexFragmentInstance = HexadecimalColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, hexFragmentInstance!!).commit()

    hsvFragmentInstance = HSVColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, hsvFragmentInstance!!).commit()

    rgbFragmentInstance = RGBColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, rgbFragmentInstance!!).commit()

    pickerFragmentInstance = ColorPickerPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, pickerFragmentInstance!!).commit()

    binding.fragmentColorPickerTabLayout.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            when (tab.text) {
                getString(R.string.fragmentColorPicker_tab_color_picker_str) -> {
                    hsvFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.fragmentColorPicker_tab_rgb_str) -> {
                    hsvFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.fragmentColorPicker_tab_hex_str) -> {
                    hsvFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.fragmentColorPicker_tab_hsv_str) -> {
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    pickerFragmentInstance!!.requireView().visibility = View.GONE
                    hsvFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}