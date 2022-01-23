package com.realtomjoney.pyxlmoose.fragments.colorpicker.hsv

import com.realtomjoney.pyxlmoose.databinding.FragmentHSVColorPickerBinding
import com.realtomjoney.pyxlmoose.listeners.HSVColorPickerFragmentListener

var binding_: FragmentHSVColorPickerBinding? = null

val binding get() = binding_!!

var hsvHue = 0f
var hsvSaturation = 0f
var hsvValue = 0f
