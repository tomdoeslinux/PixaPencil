package com.therealbluepandabear.pixapencil.fragments.colorpicker

import android.graphics.Color
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPickerFragmentListener
import com.therealbluepandabear.pixapencil.models.ColorPalette

var _binding: FragmentColorPickerBinding? = null

var oldColor_ = Color.BLACK
var colorPalette: ColorPalette? = null

val binding get(): FragmentColorPickerBinding {
    return _binding!!
}

lateinit var caller: ColorPickerFragmentListener

var prevColorPickerTab: Int = 0
