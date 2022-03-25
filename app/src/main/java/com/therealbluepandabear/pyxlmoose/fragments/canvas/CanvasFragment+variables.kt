package com.therealbluepandabear.pyxlmoose.fragments.canvas

import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding
import com.therealbluepandabear.pyxlmoose.customviews.pixelgridview.PixelGridView
import com.therealbluepandabear.pyxlmoose.listeners.CanvasFragmentListener

var binding_: FragmentCanvasBinding? = null

val binding get(): FragmentCanvasBinding {
    return binding_!!
}

lateinit var caller: CanvasFragmentListener

lateinit var myCanvasViewInstance: PixelGridView