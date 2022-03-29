package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import com.therealbluepandabear.pixapencil.databinding.FragmentNewColorPaletteBinding
import com.therealbluepandabear.pixapencil.listeners.NewColorPaletteFragmentListener

var binding_: FragmentNewColorPaletteBinding? = null

val binding get(): FragmentNewColorPaletteBinding {
    return binding_!!
}

lateinit var caller: NewColorPaletteFragmentListener