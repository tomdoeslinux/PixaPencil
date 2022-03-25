package com.therealbluepandabear.pyxlmoose.fragments.colorpicker

import android.graphics.Color
import com.therealbluepandabear.pyxlmoose.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hsv.HSVColorPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.picker.ColorPickerPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.rgb.RGBColorPickerFragment
import com.therealbluepandabear.pyxlmoose.listeners.ColorPickerFragmentListener

var binding_: FragmentColorPickerBinding? = null

var oldColor_ = Color.BLACK
var colorPaletteMode_: Boolean = false

val binding get(): FragmentColorPickerBinding {
    return binding_!!
}

lateinit var caller: ColorPickerFragmentListener

var hexFragmentInstance: HexadecimalColorPickerFragment? = null
var hsvFragmentInstance: HSVColorPickerFragment? = null
var rgbFragmentInstance: RGBColorPickerFragment? = null
var pickerFragmentInstance: ColorPickerPickerFragment? = null
