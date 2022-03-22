package com.realtomjoney.pyxlmoose.fragments.spraytoolsettings

import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.enums.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.utility.HapticFeedbackWrapper
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.LongConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun SprayToolSettingsFragment.setOnClickListeners() {
    binding.fragmentSprayToolSettingsDoneButton.setOnClickListener {
        val radius = binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString()
        val strength = binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString()

        if (Integer.parseInt(radius) !in IntConstants.SprayOptionsMin..IntConstants.SprayOptionsMax ||
            Integer.parseInt(strength) !in IntConstants.SprayOptionsMin..IntConstants.SprayOptionsMax) {
            HapticFeedbackWrapper.performHapticFeedback(binding.fragmentSprayToolSettingsDoneButton)
            binding.root.showSnackbar(
                StringConstants.ExceptionInvalidRadiusStrengthMessage,
                SnackbarDuration.Default
            )
        } else {
            hideKeyboard()

            Handler(Looper.getMainLooper()).postDelayed({
                caller.onDoneButtonPressed(radius, strength)
            }, LongConstants.DefaultHandlerDelay)
        }
    }
}