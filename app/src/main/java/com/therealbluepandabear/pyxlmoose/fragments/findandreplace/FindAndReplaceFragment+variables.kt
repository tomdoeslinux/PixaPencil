package com.therealbluepandabear.pyxlmoose.fragments.findandreplace

import com.therealbluepandabear.pyxlmoose.databinding.FragmentFindAndReplaceBinding
import com.therealbluepandabear.pyxlmoose.listeners.FindAndReplaceFragmentListener

var binding_: FragmentFindAndReplaceBinding? = null

val binding get(): FragmentFindAndReplaceBinding {
    return binding_!!
}

lateinit var caller: FindAndReplaceFragmentListener