package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import com.therealbluepandabear.pixapencil.databinding.FragmentColorPalettesBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesFragmentListener

var binding_: FragmentColorPalettesBinding? = null

val binding get(): FragmentColorPalettesBinding {
    return binding_!!
}

lateinit var caller: ColorPalettesFragmentListener