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

package com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider

import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.appinfo.AppInfoFragment
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.onMenuItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.activityMainTopAppMenu_save_project_item -> {
            supportFragmentManager.commit {
                replace(R.id.activityMain_primaryFragmentHost, AppInfoFragment.newInstance())
                addToBackStack(null)
            }
        }

        R.id.activityMainTopAppMenu_dark_light_mode_item -> {
            darkMode = !darkMode

            with(sharedPreferenceObject.edit()) {
                putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_IDENTIFIER, darkMode)
                putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_CHANGED_IDENTIFIER, true)
                apply()
            }

            if (darkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        R.id.activityMainTopAppMenu_delete_all_item -> {
            val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.generic_warning)
                .setMessage(R.string.dialog_delete_all_projects)
                .setPositiveButton(R.string.generic_ok) { _, _ ->
                    val size = pixelArtData.size

                    pixelArtViewModel.deleteAll()
                    binding.activityMainCoordinatorLayout.showSnackbar(getString(R.string.snackbar_deleted_projects, size.toString()), SnackbarDuration.Medium)
                    binding.activityMainNewProjectButton.show()
                }
                .setNegativeButton(R.string.generic_cancel, null)

            alertDialog.show()
        }

        R.id.activityMainTopAppMenu_open_image_item -> {
            galleryActivityLauncher.launch(arrayOf ("image/*"))
        }
    }

    return true
}
