package com.therealbluepandabear.pixapencil.activities.main.oncreate

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.activities.main.startSpotLight
import com.therealbluepandabear.pixapencil.extensions.getScreenHeight
import com.therealbluepandabear.pixapencil.extensions.getScreenWidth
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.showWelcomeScreenIfApplicable() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_FIRST_LAUNCH_IDENTIFIER)) {
        firstLaunch = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_FIRST_LAUNCH_IDENTIFIER, false)
    } else {
        with(sharedPreferenceObject.edit()) {
            putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_FIRST_LAUNCH_IDENTIFIER, true)
            apply()
        }

        showDialog(
            getString(R.string.dialog_welcome_title),
            getString(R.string.dialog_welcome_text),
            getString(R.string.generic_ok),
            { _, _ ->
                startSpotLight()
            }, getString(R.string.generic_no_thanks), { _, _ -> })

        if (this@showWelcomeScreenIfApplicable.getScreenWidth() < 600 && this@showWelcomeScreenIfApplicable.getScreenHeight() < 850) {
            showSimpleInfoDialog(
                getString(R.string.generic_warning),
                getString(R.string.dialog_small_screen_warning_text)
            )
        }
    }
}