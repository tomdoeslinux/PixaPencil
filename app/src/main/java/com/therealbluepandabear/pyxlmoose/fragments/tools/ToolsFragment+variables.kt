package com.therealbluepandabear.pyxlmoose.fragments.tools

import com.realtomjoney.pyxlmoose.databinding.FragmentToolsBinding
import com.therealbluepandabear.pyxlmoose.listeners.ToolsFragmentListener

var binding_: FragmentToolsBinding? = null

val binding get(): FragmentToolsBinding {
    return binding_!!
}

lateinit var caller: ToolsFragmentListener