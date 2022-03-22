package com.realtomjoney.pyxlmoose.fragments.findandreplace

import com.realtomjoney.pyxlmoose.databinding.FragmentFindAndReplaceBinding
import com.realtomjoney.pyxlmoose.listeners.FindAndReplaceFragmentListener

var binding_: FragmentFindAndReplaceBinding? = null

val binding get(): FragmentFindAndReplaceBinding {
    return binding_!!
}

lateinit var caller: FindAndReplaceFragmentListener