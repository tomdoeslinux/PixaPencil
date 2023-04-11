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

package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.extensions.doAddLast

fun CanvasActivity.rectangleToolOnActionUp() {
    if (
        viewModel.currentTool.family == ToolFamily.Rectangle &&
        viewModel.currentTool.outlined == false &&
        coordinate != null &&
        shapeOrigin != null) {
        filledRectangleAlgorithm.compute(shapeOrigin!!, coordinate!!)
    }

    shapeOrigin = null
    firstShapeDrawn = false
    coordinate = null
    viewModel.undoStack.doAddLast(viewModel.currentBitmapAction!!)
}