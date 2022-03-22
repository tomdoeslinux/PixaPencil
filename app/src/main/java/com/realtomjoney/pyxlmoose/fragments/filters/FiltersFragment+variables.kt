package com.realtomjoney.pyxlmoose.fragments.filters

import com.realtomjoney.pyxlmoose.databinding.FragmentFiltersBinding
import com.realtomjoney.pyxlmoose.listeners.FiltersFragmentListener

var binding_: FragmentFiltersBinding? = null

val binding get(): FragmentFiltersBinding {
    return binding_!!
}

lateinit var caller: FiltersFragmentListener