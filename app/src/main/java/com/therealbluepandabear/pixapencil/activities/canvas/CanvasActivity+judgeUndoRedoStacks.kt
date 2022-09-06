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

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable

fun CanvasActivity.judgeUndoRedoStacks() {
    if (viewModel.undoStack.isNotEmpty()) {
        menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
    } else {
        menu.findItem(R.id.activityCanvasTopAppMenu_undo).disable()
    }

    if (viewModel.redoStack.isEmpty()) {
        menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).disable()
    } else {
        menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
    }
}