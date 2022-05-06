package com.therealbluepandabear.pixapencil.activities.main

import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R


fun MainActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.activityMainTopAppMenu_save_project_item -> {
            MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(getString(R.string.dialog_community_title_in_code_str))
                .setMessage(R.string.dialog_community_text_in_code_str)
                .setPositiveButton(getString(R.string.dialog_positive_button_text_in_code_str)) { _, _ -> }
                .show()
                .apply {
                    findViewById<TextView>(android.R.id.message)
                        ?.movementMethod = LinkMovementMethod.getInstance()
                }
        }
    }

    return true
}
