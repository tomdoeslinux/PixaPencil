package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
//import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialogWithNeutralButtonAndOnCancelListener

fun CanvasActivity.showUnsavedChangesDialog() {
    showDialogWithNeutralButtonAndOnCancelListener(
        getString(R.string.dialog_unsaved_changes_title),
        getString(R.string.dialog_unsaved_changes_message, projectTitle),
        getString(R.string.generic_cancel),
        { _, _ ->
            viewModel.unsavedChangesDialogShown = false
        },
        getString(R.string.dialog_unsaved_changes_negative_button_text),
        { _, _ ->
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        },
        getString(R.string.activityCanvasTopAppMenu_save_text),
        { _, _ ->
            onSaveProjectOptionsItemSelected()
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        },
        {
            viewModel.unsavedChangesDialogShown = false
        }
    )
}