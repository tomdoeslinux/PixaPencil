package com.therealbluepandabear.pixapencil.activities.main.oncreate

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.activities.main.startSpotLight
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.showWelcomeScreenIfApplicable() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_FIRST_LAUNCH_IDENTIFIER)) {
        firstLaunch = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_FIRST_LAUNCH_IDENTIFIER, false)
    } else {
        with(sharedPreferenceObject.edit()) {
            putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_FIRST_LAUNCH_IDENTIFIER, true)
            apply()
        }

        val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
            .setTitle(R.string.dialog_welcome_title)
            .setMessage(R.string.dialog_welcome_text)
            .setPositiveButton(R.string.generic_ok) { _, _ ->
                startSpotLight()
            }
            .setNegativeButton(R.string.generic_no_thanks, null)

        alertDialog.show()
    }
}