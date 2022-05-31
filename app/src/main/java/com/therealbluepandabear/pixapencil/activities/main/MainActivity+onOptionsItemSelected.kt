package com.therealbluepandabear.pixapencil.activities.main

import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.activityMainTopAppMenu_save_project_item -> {
            val instance = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(getString(R.string.dialog_community_title_in_code_str))
                .setMessage(R.string.dialog_community_text_in_code_str)
                .setPositiveButton(getString(R.string.generic_ok_in_code_str)) { _, _ -> }
                .show()
            instance.findViewById<TextView>(android.R.id.message)?.movementMethod = LinkMovementMethod.getInstance()
        }

        R.id.activityMainTopAppMenu_dark_light_mode_item -> {
            darkMode = !darkMode

            with(sharedPreferenceObject.edit()) {
                putBoolean(StringConstants.Identifiers.SharedPreferenceDarkLightModeIdentifier, darkMode)
                putBoolean(StringConstants.Identifiers.SharedPreferenceDarkLightModeChanged, true)
                apply()
            }

            if (darkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    return true
}
