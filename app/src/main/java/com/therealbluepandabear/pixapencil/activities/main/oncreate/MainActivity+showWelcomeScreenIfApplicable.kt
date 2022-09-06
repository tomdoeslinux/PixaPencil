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