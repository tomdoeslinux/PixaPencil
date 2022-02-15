package com.realtomjoney.pyxlmoose.fragments.spraytoolsettings

import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.utility.LongConstants

fun SprayToolSettingsFragment.setOnClickListeners() {
    binding.fragmentSprayToolSettingsDoneButton.setOnClickListener {
        val radius = binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString()
        val strength = binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString()

        hideKeyboard()

        Handler(Looper.getMainLooper()).postDelayed({
            caller.onDoneButtonPressed(radius, strength)
        }, LongConstants.DEF_HANDLER_DELAY)
    }
}