package com.therealbluepandabear.pixapencil.fragments.newcanvas

import com.therealbluepandabear.pixapencil.databinding.FragmentNewCanvasBinding
import com.therealbluepandabear.pixapencil.listeners.NewCanvasFragmentListener

var binding_: FragmentNewCanvasBinding? = null

val binding get(): FragmentNewCanvasBinding {
    return binding_!!
}

lateinit var caller: NewCanvasFragmentListener