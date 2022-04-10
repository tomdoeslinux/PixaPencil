package com.therealbluepandabear.pixapencil.extensions

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.utility.StringConstants


fun Activity.showDialog(
    dialogTitle: String,
    dialogMessage: String,
    dialogPositiveButtonText: String,
    dialogPositiveButtonAction: DialogInterface.OnClickListener,
    dialogNegativeButtonText: String?,
    dialogNegativeButtonAction: DialogInterface.OnClickListener?,
    view: View?) {
    MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(dialogTitle)
        .setMessage(dialogMessage)
        .setView(view)
        .setPositiveButton(dialogPositiveButtonText, dialogPositiveButtonAction)
        .setNegativeButton(dialogNegativeButtonText, dialogNegativeButtonAction)
        .show()
}

fun Activity.showSimpleInfoDialog(
    dialogTitle: String,
    dialogMessage: String) {
    MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(dialogTitle)
        .setMessage(dialogMessage)
        .setPositiveButton(StringConstants.DialogPositiveButtonText, null)
        .show()
}