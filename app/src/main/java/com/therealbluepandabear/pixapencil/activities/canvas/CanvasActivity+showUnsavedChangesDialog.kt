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

package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
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