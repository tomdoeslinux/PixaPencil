package com.realtomjoney.pyxlmoose.fragments.colorpicker

import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerBinding
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener

var binding_: FragmentColorPickerBinding? = null

val binding get() = binding_!!

lateinit var caller: ColorPickerFragmentListener

var valueR = 0
var valueG = 0
var valueB = 0