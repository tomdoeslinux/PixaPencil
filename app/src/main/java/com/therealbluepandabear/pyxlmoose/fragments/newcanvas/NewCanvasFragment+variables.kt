package com.therealbluepandabear.pyxlmoose.fragments.newcanvas

import com.realtomjoney.pyxlmoose.databinding.FragmentNewCanvasBinding
import com.therealbluepandabear.pyxlmoose.listeners.NewCanvasFragmentListener

var binding_: FragmentNewCanvasBinding? = null

val binding get(): FragmentNewCanvasBinding {
    return binding_!!
}

lateinit var caller: NewCanvasFragmentListener