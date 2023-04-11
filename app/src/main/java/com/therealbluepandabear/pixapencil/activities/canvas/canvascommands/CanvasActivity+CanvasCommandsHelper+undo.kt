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

package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onactioncompleted.extendedOnUndoActionCompleted
import com.therealbluepandabear.pixapencil.extensions.doAddLast
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinate

fun CanvasActivity.CanvasCommandsHelper.undo() {
    if (baseReference.viewModel.undoStack.size > 0) {
        for ((key, value) in baseReference.viewModel.undoStack.last().actionData.distinctBy {
            it.coordinate
        }) {
            baseReference.binding.activityCanvasDrawingView.drawingViewBitmap.setPixel(Coordinate(key.x, key.y), value)
        }

        baseReference.viewModel.redoStack.doAddLast(baseReference.viewModel.undoStack.last())

        baseReference.binding.activityCanvasDrawingView.invalidate()
        baseReference.viewModel.undoStack.removeLast()
    }

    baseReference.extendedOnUndoActionCompleted(baseReference.viewModel.redoStack, baseReference.viewModel.undoStack)
}