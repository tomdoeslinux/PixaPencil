package com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb

import com.therealbluepandabear.pixapencil.databinding.FragmentRGBColorPickerBinding

var binding_: FragmentRGBColorPickerBinding? = null

val binding get(): FragmentRGBColorPickerBinding {
    return binding_!!
}

var valueR = 0f
var valueG = 0f
var valueB = 0f

