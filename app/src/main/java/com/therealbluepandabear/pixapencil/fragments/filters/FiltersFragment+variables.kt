package com.therealbluepandabear.pixapencil.fragments.filters

import com.therealbluepandabear.pixapencil.databinding.FragmentFiltersBinding
import com.therealbluepandabear.pixapencil.listeners.FiltersFragmentListener

var binding_: FragmentFiltersBinding? = null

val binding get(): FragmentFiltersBinding {
    return binding_!!
}

lateinit var caller: FiltersFragmentListener