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
            when (tab.position) {
                0 -> {
                    hexFragmentInstance?.view?.visibility = View.GONE
                    rgbFragmentInstance?.view?.visibility = View.GONE
                    pickerFragmentInstance?.view?.visibility = View.VISIBLE

                    currentTab = 0
                }
                1 -> {
                    hexFragmentInstance?.view?.visibility = View.GONE
                    pickerFragmentInstance?.view?.visibility = View.GONE
                    rgbFragmentInstance?.view?.visibility = View.VISIBLE

                    currentTab = 1
                }
                2 -> {
                    rgbFragmentInstance?.view?.visibility = View.GONE
                    pickerFragmentInstance?.view?.visibility = View.GONE
                    hexFragmentInstance?.view?.visibility = View.VISIBLE

                    currentTab = 2
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}