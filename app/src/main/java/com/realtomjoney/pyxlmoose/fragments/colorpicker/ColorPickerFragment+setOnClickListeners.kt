package com.realtomjoney.pyxlmoose.fragments.colorpicker

import android.view.View
import com.google.android.material.tabs.TabLayout
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.colorpicker.hsv.HSVColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.colorpicker.rgb.RGBColorPickerFragment

fun ColorPickerFragment.setOnClickListeners() {
    hexFragmentInstance = HexadecimalColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, hexFragmentInstance!!).commit()

    hsvFragmentInstance = HSVColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, hsvFragmentInstance!!).commit()

    rgbFragmentInstance = RGBColorPickerFragment.newInstance()
    activity!!.supportFragmentManager.beginTransaction().add(R.id.fragmentColorPicker_tabLayoutFragmentHost, rgbFragmentInstance!!).commit()

    binding.fragmentColorPickerTabLayout.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            when (tab.text) {
                getString(R.string.tab_rgb) -> {
                    hsvFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.tab_hex) -> {
                    hsvFragmentInstance!!.requireView().visibility = View.GONE
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
                getString(R.string.tab_hsv) -> {
                    rgbFragmentInstance!!.requireView().visibility = View.GONE
                    hexFragmentInstance!!.requireView().visibility = View.GONE
                    hsvFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}