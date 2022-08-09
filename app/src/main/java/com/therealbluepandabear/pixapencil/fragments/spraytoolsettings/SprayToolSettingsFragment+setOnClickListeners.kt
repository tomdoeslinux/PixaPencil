package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

import androidx.core.widget.doAfterTextChanged
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

var invalidRadius = false
var invalidStrength = false

private fun SprayToolSettingsFragment.checkForRadiusError() {
    when (binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = getString(R.string.exception_empty_spray_radius)
            invalidRadius = true
        }

        !in IntConstants.SPRAY_OPTIONS_MIN..IntConstants.SPRAY_OPTIONS_MAX -> {
            binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = getString(R.string.exception_invalid_spray_radius)
            invalidRadius = true
        }

        else -> {
            binding.fragmentSprayToolSettingsRadiusTextInputLayout.isErrorEnabled = false
            invalidRadius = false
        }
    }
}

private fun SprayToolSettingsFragment.checkForStrengthError() {
    when (binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = getString(R.string.exception_empty_spray_strength)
            invalidStrength = true
        }

        !in IntConstants.SPRAY_OPTIONS_MIN..IntConstants.SPRAY_OPTIONS_MAX -> {
            binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = getString(R.string.exception_invalid_spray_strength)
            invalidStrength = true
        }

        else -> {
            binding.fragmentSprayToolSettingsStrengthTextInputLayout.isErrorEnabled = false
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

            val parsedRadius = radius.toInt()
            val parsedStrength = strength.toInt()

            with (paramSharedPreferenceObject.edit()) {
                putInt(StringConstants.Identifiers.SHARED_PREFERENCE_SPRAY_RADIUS_IDENTIFIER, parsedRadius)
                putInt(StringConstants.Identifiers.SHARED_PREFERENCE_SPRAY_STRENGTH_IDENTIFIER, parsedStrength)
                apply()
            }

            caller.onDoneButtonPressed(radius, strength)
        }
    }
}