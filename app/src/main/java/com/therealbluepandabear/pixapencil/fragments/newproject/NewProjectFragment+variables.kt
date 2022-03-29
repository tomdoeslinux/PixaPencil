package com.therealbluepandabear.pixapencil.fragments.newproject

import com.therealbluepandabear.pixapencil.databinding.FragmentNewProjectBinding
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener

var binding_: FragmentNewProjectBinding? = null

val binding get(): FragmentNewProjectBinding {
    return binding_!!
}

lateinit var caller: NewProjectFragmentListener