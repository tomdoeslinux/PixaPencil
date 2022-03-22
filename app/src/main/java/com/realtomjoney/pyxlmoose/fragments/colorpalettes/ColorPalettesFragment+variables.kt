package com.realtomjoney.pyxlmoose.fragments.colorpalettes

import com.realtomjoney.pyxlmoose.databinding.FragmentColorPalettesBinding
import com.realtomjoney.pyxlmoose.listeners.ColorPalettesFragmentListener

var binding_: FragmentColorPalettesBinding? = null

val binding get(): FragmentColorPalettesBinding {
    return binding_!!
}

lateinit var caller: ColorPalettesFragmentListener