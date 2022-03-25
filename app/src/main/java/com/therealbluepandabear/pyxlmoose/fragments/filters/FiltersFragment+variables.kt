package com.therealbluepandabear.pyxlmoose.fragments.filters

import com.realtomjoney.pyxlmoose.databinding.FragmentFiltersBinding
import com.therealbluepandabear.pyxlmoose.listeners.FiltersFragmentListener

var binding_: FragmentFiltersBinding? = null

val binding get(): FragmentFiltersBinding {
    return binding_!!
}

lateinit var caller: FiltersFragmentListener