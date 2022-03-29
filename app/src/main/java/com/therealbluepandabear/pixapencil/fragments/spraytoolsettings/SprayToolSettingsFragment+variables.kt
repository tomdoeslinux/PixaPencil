package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

import com.therealbluepandabear.pixapencil.databinding.FragmentSprayToolSettingsBinding
import com.therealbluepandabear.pixapencil.listeners.SprayToolSettingsFragmentListener

var binding_: FragmentSprayToolSettingsBinding? = null

val binding get(): FragmentSprayToolSettingsBinding {
    return binding_!!
}

lateinit var caller: SprayToolSettingsFragmentListener
