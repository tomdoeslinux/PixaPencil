package com.therealbluepandabear.pixapencil.activities.main

import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R


fun MainActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.activityMainTopAppMenu_community_item -> {
            MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle("Community")
                .setMessage(R.string.foo)
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
