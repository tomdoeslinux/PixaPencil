package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MainActivity.showWelcomeScreenIfApplicable() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceFirstLaunchIdentifier)) {
        firstLaunch = false
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
    }
}