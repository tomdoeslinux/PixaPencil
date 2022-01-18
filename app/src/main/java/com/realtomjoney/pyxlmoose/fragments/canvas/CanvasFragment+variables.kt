package com.realtomjoney.pyxlmoose.fragments.canvas

import com.realtomjoney.pyxlmoose.customviews.mycanvasview.PixelGridView
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener

var binding_: FragmentCanvasBinding? = null

val binding get() = binding_!!

lateinit var caller: CanvasFragmentListener

lateinit var myCanvasViewInstance: PixelGridView