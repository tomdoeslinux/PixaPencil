package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.LongConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun SprayToolSettingsFragment.warnOfIncorrectValues() {
    HapticFeedbackWrapper.performHapticFeedback(binding.fragmentSprayToolSettingsDoneButton)
    binding.root.showSnackbar(
        getString(R.string.exception_invalid_radius_strength_message_in_code_str),
        SnackbarDuration.Default
    )
}

fun SprayToolSettingsFragment.setOnClickListeners() {
    binding.fragmentSprayToolSettingsDoneButton.setOnClickListener {
        val radius = binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString()
        val strength = binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString()

        val parsedRadius = Integer.parseInt(radius)
        val parsedStrength = Integer.parseInt(strength)

        if (parsedRadius !in IntConstants.SprayOptionsMin..IntConstants.SprayOptionsMax ||
            parsedStrength !in IntConstants.SprayOptionsMin..IntConstants.SprayOptionsMax) {
            warnOfIncorrectValues()
        } else {
            with (sharedPreferenceObject.edit()) {
                putInt(StringConstants.SharedPreferencesSprayRadiusIdentifier, parsedRadius)
                putInt(StringConstants.SharedPreferencesSprayStrengthIdentifier, parsedStrength)
                apply()
            }

            hideKeyboard()

            Handler(Looper.getMainLooper()).postDelayed({
                caller.onDoneButtonPressed(radius, strength)
            }, LongConstants.DefaultHandlerDelay)
        }
    }
}