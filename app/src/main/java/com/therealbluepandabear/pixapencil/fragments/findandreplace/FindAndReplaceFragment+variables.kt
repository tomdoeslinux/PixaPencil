package com.therealbluepandabear.pixapencil.fragments.findandreplace

import com.therealbluepandabear.pixapencil.databinding.FragmentFindAndReplaceBinding
import com.therealbluepandabear.pixapencil.listeners.FindAndReplaceFragmentListener

var binding_: FragmentFindAndReplaceBinding? = null

val binding get(): FragmentFindAndReplaceBinding {
    return binding_!!
}

lateinit var caller: FindAndReplaceFragmentListener