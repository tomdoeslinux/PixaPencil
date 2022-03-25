package com.therealbluepandabear.pyxlmoose.fragments.spraytoolsettings

import com.therealbluepandabear.pyxlmoose.databinding.FragmentSprayToolSettingsBinding
import com.therealbluepandabear.pyxlmoose.listeners.SprayToolSettingsFragmentListener

var binding_: FragmentSprayToolSettingsBinding? = null

val binding get(): FragmentSprayToolSettingsBinding {
    return binding_!!
}

lateinit var caller: SprayToolSettingsFragmentListener
