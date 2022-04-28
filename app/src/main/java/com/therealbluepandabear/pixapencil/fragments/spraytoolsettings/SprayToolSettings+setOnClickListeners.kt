package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

import android.os.Handler
import android.os.Looper
import androidx.core.widget.doAfterTextChanged
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.LongConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

var invalidRadius = false
var invalidStrength = false

private fun SprayToolSettingsFragment.checkForRadiusError() {
    when (binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = getString(R.string.exception_empty_spray_radius_in_code_str)
            invalidRadius = true
        }

        !in IntConstants.SprayOptionsMin..IntConstants.SprayOptionsMax -> {
            binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = getString(R.string.exception_invalid_spray_radius_in_code_str)
            invalidRadius = true
        }

        else -> {
            binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = null
            invalidRadius = false
        }
    }
}

private fun SprayToolSettingsFragment.checkForStrengthError() {
    when (binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = getString(R.string.exception_empty_spray_strength_in_code_str)
            invalidStrength = true
        }

        !in IntConstants.SprayOptionsMin..IntConstants.SprayOptionsMax -> {
            binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = getString(R.string.exception_invalid_spray_strength_in_code_str)
            invalidStrength = true
        }

        else -> {
            binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = null
            invalidStrength = false
        }
    }
}

fun SprayToolSettingsFragment.setOnClickListeners() {
    binding.fragmentSprayToolSettingsRadiusTextInputEditText.doAfterTextChanged {
        checkForRadiusError()
    }

    binding.fragmentSprayToolSettingsStrengthTextInputEditText.doAfterTextChanged {
        checkForStrengthError()
    }

    binding.fragmentSprayToolSettingsDoneButton.setOnClickListener {
        checkForRadiusError()
        checkForStrengthError()

        if (!invalidRadius && !invalidStrength) {
            val radius = binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString()
            val strength = binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString()

            val parsedRadius = Integer.parseInt(radius)
            val parsedStrength = Integer.parseInt(strength)

            with (sharedPreferenceObject.edit()) {
                putInt(StringConstants.Identifiers.SharedPreferencesSprayRadiusIdentifier, parsedRadius)
                putInt(StringConstants.Identifiers.SharedPreferencesSprayStrengthIdentifier, parsedStrength)
                apply()
            }

            hideKeyboard()

            Handler(Looper.getMainLooper()).postDelayed({
                caller.onDoneButtonPressed(radius, strength)
            }, LongConstants.DefaultHandlerDelay)
        }
    }
}