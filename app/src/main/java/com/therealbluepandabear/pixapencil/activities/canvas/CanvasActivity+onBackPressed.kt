package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialogWithNeutralButton
import com.therealbluepandabear.pixapencil.utility.constants.Flags

fun CanvasActivity.extendedOnBackPressed() {
    Flags.PressedBackToExit = true

    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStackImmediate()

        binding.root.post {
            judgeUndoRedoStacks()
        }
    } else if (!saved) {
        showDialogWithNeutralButton(
            getString(R.string.dialog_unsaved_changes_title_in_code_str),
            getString(R.string.dialog_unsaved_changes_message_in_code_str, projectTitle),
            getString(R.string.dialog_unsaved_changes_button_text_in_code_str),
            { _, _ -> },
            getString(R.string.dialog_unsaved_changes_negative_button_text_in_code_str),
            { _, _ ->
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            },
            getString(R.string.dialog_unsaved_changes_positive_button_text_in_code_str),
            { _, _ ->
                onSaveProjectOptionsItemSelected()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            })
    } else {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }
}