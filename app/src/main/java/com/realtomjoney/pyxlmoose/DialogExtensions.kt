package com.realtomjoney.pyxlmoose

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Activity.showDialog(
    dialogTitle: String,
    dialogMessage: String,
    dialogPositiveButtonText: String,
    dialogPositiveButtonAction: DialogInterface.OnClickListener,
    dialogNegativeButtonText: String?,
    dialogNegativeButtonAction: DialogInterface.OnClickListener?,
    view: View?) {
    MaterialAlertDialogBuilder(this)
        .setTitle(dialogTitle.replaceFirstChar { it.titlecase() })
        .setMessage(dialogMessage.replaceFirstChar { it.titlecase() })
        .setView(view)
        .setPositiveButton(dialogPositiveButtonText.replaceFirstChar { it.titlecase() }, dialogPositiveButtonAction)
        .setNegativeButton(dialogNegativeButtonText?.replaceFirstChar { it.titlecase() }, dialogNegativeButtonAction)
        .show()
}
