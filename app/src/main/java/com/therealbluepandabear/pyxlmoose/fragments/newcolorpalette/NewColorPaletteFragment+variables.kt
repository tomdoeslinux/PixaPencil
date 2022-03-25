package com.therealbluepandabear.pyxlmoose.fragments.newcolorpalette

import com.realtomjoney.pyxlmoose.databinding.FragmentNewColorPaletteBinding
import com.therealbluepandabear.pyxlmoose.listeners.NewColorPaletteFragmentListener

var binding_: FragmentNewColorPaletteBinding? = null

val binding get(): FragmentNewColorPaletteBinding {
    return binding_!!
}

lateinit var caller: NewColorPaletteFragmentListener