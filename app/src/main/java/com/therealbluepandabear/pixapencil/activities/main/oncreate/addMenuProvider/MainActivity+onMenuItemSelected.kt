package com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider

import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showDialog
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
            showDialog(
                getString(R.string.generic_warning),
                getString(R.string.dialog_delete_all_projects),
                getString(R.string.generic_ok), { _, _ ->
                    val size = pixelArtData.size

                    pixelArtViewModel.deleteAll()
                    binding.activityMainCoordinatorLayout.showSnackbar(getString(R.string.snackbar_deleted_projects, size.toString()), SnackbarDuration.Medium)
                },  getString(R.string.generic_cancel), null)
        }

        R.id.activityMainTopAppMenu_open_image_item -> {
            galleryActivityLauncher.launch(arrayOf ("image/*"))
        }
    }

    return true
}
