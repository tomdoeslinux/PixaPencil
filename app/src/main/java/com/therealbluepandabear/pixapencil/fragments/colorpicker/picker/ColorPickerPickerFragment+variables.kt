package com.therealbluepandabear.pixapencil.fragments.colorpicker.picker

import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerPickerBinding

var binding_: FragmentColorPickerPickerBinding? = null

val binding get(): FragmentColorPickerPickerBinding {
    return binding_!!
}

var selectedColor: Int = 0
