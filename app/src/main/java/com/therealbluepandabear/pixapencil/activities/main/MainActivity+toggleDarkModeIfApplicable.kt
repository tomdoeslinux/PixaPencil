package com.therealbluepandabear.pixapencil.activities.main

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.toggleDarkModeIfApplicable() {
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO -> {
            darkMode = if (!sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_CHANGED_IDENTIFIER, false)) {
                with(sharedPreferenceObject.edit()) {
                    putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_IDENTIFIER, false)
                    apply()
                }

                false
            } else {
                sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_IDENTIFIER, darkMode)
            }
        }

        Configuration.UI_MODE_NIGHT_YES -> {
            darkMode = if (!sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_CHANGED_IDENTIFIER, false)) {
                with(sharedPreferenceObject.edit()) {
                    putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_IDENTIFIER, true)
                    apply()
                }

                true
            } else {
                sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_IDENTIFIER, darkMode)
            }
        }
    }

    if (darkMode) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}