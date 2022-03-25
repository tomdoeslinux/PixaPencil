package com.therealbluepandabear.pyxlmoose.fragments.brushes

import com.therealbluepandabear.pyxlmoose.databinding.FragmentBrushesBinding
import com.therealbluepandabear.pyxlmoose.listeners.BrushesFragmentListener

var binding_: FragmentBrushesBinding? = null

val binding get(): FragmentBrushesBinding {
    return binding_!!
}

lateinit var caller: BrushesFragmentListener