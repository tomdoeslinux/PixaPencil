package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.navigateHome
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.utility.StringConstants

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
        menu.showItems()
        switchSelectedColorIndicator()
    } else {
        startActivity(Intent(context, MainActivity::class.java))
    }
}