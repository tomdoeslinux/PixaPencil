package com.realtomjoney.pyxlmoose.fragments.tools

import com.realtomjoney.pyxlmoose.databinding.FragmentToolsBinding
import com.realtomjoney.pyxlmoose.listeners.ToolsFragmentListener

var binding_: FragmentToolsBinding? = null

val binding get(): FragmentToolsBinding {
    return binding_!!
}

lateinit var caller: ToolsFragmentListener