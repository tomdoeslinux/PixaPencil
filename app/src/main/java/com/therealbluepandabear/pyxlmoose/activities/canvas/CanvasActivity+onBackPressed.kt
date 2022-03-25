package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pyxlmoose.activities.main.MainActivity
import com.therealbluepandabear.pyxlmoose.extensions.navigateHome
import com.therealbluepandabear.pyxlmoose.extensions.showDialog
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnBackPressed() {
    if (!saved && currentFragmentInstance == null) {
        showDialog(
            StringConstants.DialogUnsavedChangesTitle,
            StringConstants.DialogUnsavedChangesMessage,
            StringConstants.DialogPositiveButtonText,
            { _, _ ->
                startActivity(Intent(context, MainActivity::class.java))
            }, StringConstants.DialogNegativeButtonText, { _, _ -> }, null)
    } else if (currentFragmentInstance != null) {
        navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra(StringConstants.ProjectTitleExtra)!!)
        currentFragmentInstance = null
        showMenuItems()
        switchSelectedColorIndicator()
    } else {
        startActivity(Intent(context, MainActivity::class.java))
    }
}