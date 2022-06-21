package com.therealbluepandabear.pixapencil.extensions

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import android.view.WindowManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R

fun Activity.showDialog(
    dialogTitle: String,
    dialogMessage: String?,
    dialogPositiveButtonText: String,
    dialogPositiveButtonAction: DialogInterface.OnClickListener,
    dialogNegativeButtonText: String?,
    dialogNegativeButtonAction: DialogInterface.OnClickListener?,
    view: View? = null,
    dimBackground: Boolean = true) {
    val builder = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(dialogTitle)
        .setMessage(dialogMessage)
        .setView(view)
        .setPositiveButton(dialogPositiveButtonText, dialogPositiveButtonAction)
        .setNegativeButton(dialogNegativeButtonText, dialogNegativeButtonAction)

    if (!dimBackground) {
        builder.show().window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    } else {
        builder.show()
    }
}

fun Activity.showDialogWithNeutralButtonAndOnCancelListener(
    dialogTitle: String,
    dialogMessage: String?,
    dialogPositiveButtonText: String,
    dialogPositiveButtonAction: DialogInterface.OnClickListener,
    dialogNegativeButtonText: String?,
    dialogNegativeButtonAction: DialogInterface.OnClickListener?,
    dialogNeutralButtonText: String,
    dialogNeutralButtonAction: DialogInterface.OnClickListener?,
    dialogOnCancelListener: DialogInterface.OnCancelListener?,
    view: View? = null) {
    MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(dialogTitle)
        .setMessage(dialogMessage)
        .setView(view)
        .setPositiveButton(dialogPositiveButtonText, dialogPositiveButtonAction)
        .setNegativeButton(dialogNegativeButtonText, dialogNegativeButtonAction)
        .setNeutralButton(dialogNeutralButtonText, dialogNeutralButtonAction)
        .setOnCancelListener(dialogOnCancelListener)
        .show()
}

fun Activity.showSimpleInfoDialog(
    dialogTitle: String,
    dialogMessage: String) {
    MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(dialogTitle)
        .setMessage(dialogMessage)
        .setPositiveButton(getString(R.string.generic_ok_in_code_str), null)
        .show()
}