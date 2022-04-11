package com.therealbluepandabear.pixapencil.fragments.canvas

import com.therealbluepandabear.pixapencil.customviews.pixelgridview.PixelGridView
import com.therealbluepandabear.pixapencil.databinding.FragmentCanvasBinding
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener

var binding_: FragmentCanvasBinding? = null

val binding get(): FragmentCanvasBinding {
    return binding_!!
}

lateinit var caller: CanvasFragmentListener

