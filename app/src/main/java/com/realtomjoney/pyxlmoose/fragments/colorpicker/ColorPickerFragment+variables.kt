package com.realtomjoney.pyxlmoose.fragments.colorpicker

import android.graphics.Color
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerBinding
import com.realtomjoney.pyxlmoose.fragments.colorpicker.hex.HexadecimalColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.colorpicker.hsv.HSVColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.colorpicker.rgb.RGBColorPickerFragment
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener

var binding_: FragmentColorPickerBinding? = null

var oldColor_ = Color.BLACK
var colorPaletteMode_: Boolean = false

val binding get() = binding_!!

lateinit var caller: ColorPickerFragmentListener

var hexFragmentInstance: HexadecimalColorPickerFragment? = null
var hsvFragmentInstance: HSVColorPickerFragment? = null
var rgbFragmentInstance: RGBColorPickerFragment? = null
