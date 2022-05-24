package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.utility.constants.Flags

fun CanvasActivity.extendedOnBackPressed() {
    Flags.PressedBackToExit = true

    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStackImmediate()

        binding.root.post {
            judgeUndoRedoStacks()
        }
    } else if (!saved) {
        showDialog(
            getString(R.string.dialog_unsaved_changes_title_in_code_str),
            getString(R.string.dialog_unsaved_changes_message_in_code_str),
            getString(R.string.dialog_positive_button_text_in_code_str),
            { _, _ ->
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            },  getString(R.string.dialog_negative_button_text_in_code_str), { _, _ -> })
    } else {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }
}