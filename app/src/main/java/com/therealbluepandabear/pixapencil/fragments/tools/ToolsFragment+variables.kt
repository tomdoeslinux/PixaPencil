package com.therealbluepandabear.pixapencil.fragments.tools

import com.therealbluepandabear.pixapencil.databinding.FragmentToolsBinding
import com.therealbluepandabear.pixapencil.listeners.ToolsFragmentListener

var binding_: FragmentToolsBinding? = null

val binding get(): FragmentToolsBinding {
    return binding_!!
}

lateinit var caller: ToolsFragmentListener