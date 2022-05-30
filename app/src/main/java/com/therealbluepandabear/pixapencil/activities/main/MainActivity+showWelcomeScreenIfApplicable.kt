package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.getScreenHeight
import com.therealbluepandabear.pixapencil.extensions.getScreenWidth
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.showWelcomeScreenIfApplicable() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceFirstLaunchIdentifier)) {
        firstLaunch = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceFirstLaunchIdentifier, false)
    } else {
        with(sharedPreferenceObject.edit()) {
            putBoolean(StringConstants.Identifiers.SharedPreferenceFirstLaunchIdentifier, true)
            apply()
        }

        showSimpleInfoDialog(
            getString(R.string.dialog_welcome_title_in_code_str),
            getString(R.string.dialog_welcome_text_in_code_str)
        )

        if (this@showWelcomeScreenIfApplicable.getScreenWidth() < 600 && this@showWelcomeScreenIfApplicable.getScreenHeight() < 850) {
            showSimpleInfoDialog(
                getString(R.string.dialog_large_canvas_warning_title_in_code_str),
                "You seem to have a small screen size, PixaPencil is currently incompatible with screens of your size."
            )
        }
    }
}