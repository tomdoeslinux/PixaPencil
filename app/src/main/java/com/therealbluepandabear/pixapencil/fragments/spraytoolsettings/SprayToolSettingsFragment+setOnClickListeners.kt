/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
            if (binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString().isEmpty()) {
                binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = getString(R.string.exception_empty_spray_radius)
            } else {
                binding.fragmentSprayToolSettingsRadiusTextInputLayout.error = getString(R.string.exception_invalid_spray_radius)
            }

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
            if (binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString().isEmpty()) {
                binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = getString(R.string.exception_empty_spray_strength)
            } else {
                binding.fragmentSprayToolSettingsStrengthTextInputLayout.error = getString(R.string.exception_invalid_spray_strength)
            }

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