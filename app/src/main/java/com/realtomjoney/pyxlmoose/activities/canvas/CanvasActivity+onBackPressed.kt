package com.realtomjoney.pyxlmoose.activities.canvas

import android.content.Intent
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnBackPressed() {
    if (!saved && currentFragmentInstance == null) {
        showDialog(
            StringConstants.DIALOG_UNSAVED_CHANGES_TITLE,
            StringConstants.DIALOG_UNSAVED_CHANGES_MESSAGE,
            StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
            { _, _ ->
                startActivity(Intent(context, MainActivity::class.java))
            }, StringConstants.DIALOG_NEGATIVE_BUTTON_TEXT, { _, _ -> }, null)
    } else if (currentFragmentInstance != null) {
        navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)
        currentFragmentInstance = null
        showMenuItems()
    } else {
        startActivity(Intent(context, MainActivity::class.java))
    }
}