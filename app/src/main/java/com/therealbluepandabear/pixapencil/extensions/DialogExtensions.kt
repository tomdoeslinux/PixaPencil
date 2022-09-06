/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.extensions

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R

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
        .setPositiveButton(getString(R.string.generic_ok), null)
        .show()
}