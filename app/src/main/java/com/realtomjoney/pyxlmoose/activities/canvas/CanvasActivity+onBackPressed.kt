package com.realtomjoney.pyxlmoose.activities.canvas

import android.content.Intent
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.utility.StringConstants

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