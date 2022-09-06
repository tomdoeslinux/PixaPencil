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