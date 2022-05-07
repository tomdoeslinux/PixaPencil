package com.therealbluepandabear.pixapencil.fragments.colorpicker

import android.graphics.Color
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pixapencil.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.picker.ColorPickerPickerFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb.RGBColorPickerFragment
import com.therealbluepandabear.pixapencil.listeners.ColorPickerFragmentListener

var binding_: FragmentColorPickerBinding? = null

var oldColor_ = Color.BLACK
var colorPaletteMode_: Boolean = false

val binding get(): FragmentColorPickerBinding {
    return binding_!!
}

lateinit var caller: ColorPickerFragmentListener

var hexFragmentInstance: HexadecimalColorPickerFragment? = null
var rgbFragmentInstance: RGBColorPickerFragment? = null
var pickerFragmentInstance: ColorPickerPickerFragment? = null

var prevColorPickerTab: Int = 0

var currentTab = 0