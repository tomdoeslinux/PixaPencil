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

package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.clearCanvas
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks

fun CanvasActivity.onClearCanvasOptionsItemSelected() {
    val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(R.string.dialog_clear_canvas_title)
        .setMessage(R.string.dialog_clear_canvas_message)
        .setPositiveButton(R.string.generic_ok) { _, _ ->
            canvasCommandsHelperInstance.clearCanvas()
            judgeUndoRedoStacks()
        }
        .setNegativeButton(R.string.generic_cancel, null)

    alertDialog.show()
}