package com.realtomjoney.pyxlmoose.fragments.colorpicker

import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerBinding
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener

var _binding: FragmentColorPickerBinding? = null

val binding get() = _binding!!

lateinit var caller: ColorPickerFragmentListener

var valueR = 0
var valueG = 0
var valueB = 0