package com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hsv

import com.therealbluepandabear.pyxlmoose.databinding.FragmentHSVColorPickerBinding

var binding_: FragmentHSVColorPickerBinding? = null

val binding get(): FragmentHSVColorPickerBinding {
    return binding_!!
}

var hsvHue = 0f
var hsvSaturation = 0f
var hsvValue = 0f
