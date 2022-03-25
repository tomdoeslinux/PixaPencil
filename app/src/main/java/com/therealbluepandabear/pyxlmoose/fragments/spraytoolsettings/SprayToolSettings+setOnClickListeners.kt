package com.therealbluepandabear.pyxlmoose.fragments.spraytoolsettings

import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pyxlmoose.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pyxlmoose.enums.SnackbarDuration
import com.therealbluepandabear.pyxlmoose.extensions.hideKeyboard
import com.therealbluepandabear.pyxlmoose.extensions.showSnackbar
import com.therealbluepandabear.pyxlmoose.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pyxlmoose.utility.IntConstants
import com.therealbluepandabear.pyxlmoose.utility.LongConstants
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun warnOfIncorrectValues() {
    HapticFeedbackWrapper.performHapticFeedback(binding.fragmentSprayToolSettingsDoneButton)
    binding.root.showSnackbar(
        StringConstants.ExceptionInvalidRadiusStrengthMessage,
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