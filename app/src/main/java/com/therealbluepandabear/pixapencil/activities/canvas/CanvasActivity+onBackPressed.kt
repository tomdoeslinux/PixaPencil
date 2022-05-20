package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.utility.Flags

fun CanvasActivity.extendedOnBackPressed() {
    Flags.PressedBackToExit = true

    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStackImmediate()
    } else if (!saved) {
        showDialog(
            getString(R.string.dialog_unsaved_changes_title_in_code_str),
            getString(R.string.dialog_unsaved_changes_message_in_code_str),
            getString(R.string.dialog_positive_button_text_in_code_str),
            { _, _ ->
                finish()
            },  getString(R.string.dialog_negative_button_text_in_code_str), { _, _ -> })
    } else {
        finish()
    }
}