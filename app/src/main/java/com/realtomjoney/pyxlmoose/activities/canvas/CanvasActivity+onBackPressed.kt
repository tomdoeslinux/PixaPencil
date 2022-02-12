package com.realtomjoney.pyxlmoose.activities.canvas

import android.content.Intent
import android.view.View
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

        if (isPrimaryColorSelected) {
            binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
            binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
        } else {
            binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
            binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
        }
    } else {
        startActivity(Intent(context, MainActivity::class.java))
    }
}