package com.therealbluepandabear.pixapencil.fragments.brushes

import com.therealbluepandabear.pixapencil.databinding.FragmentBrushesBinding
import com.therealbluepandabear.pixapencil.listeners.BrushesFragmentListener

var binding_: FragmentBrushesBinding? = null

val binding get(): FragmentBrushesBinding {
    return binding_!!
}

lateinit var caller: BrushesFragmentListener