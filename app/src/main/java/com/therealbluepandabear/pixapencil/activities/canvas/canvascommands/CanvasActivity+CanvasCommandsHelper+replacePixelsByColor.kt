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
import com.therealbluepandabear.pixapencil.extensions.doAddLast
import com.therealbluepandabear.pixapencil.extensions.replacePixelsByColor
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.CanvasCommandsHelper.replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
    baseReference.viewModel.saved = false

    baseReference.viewModel.currentBitmapAction = BitmapAction(mutableListOf())

    baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.replacePixelsByColor(colorToFind, colorToReplace) {
        overrideSetPixel(it, colorToReplace, true, ignoreSymmetry = true, ignoreDither = true, ignoreShadingMap = true)
    }

    baseReference.viewModel.undoStack.doAddLast(baseReference.viewModel.currentBitmapAction!!)
    baseReference.viewModel.currentBitmapAction = null

    baseReference.binding.activityCanvasPixelGridView.invalidate()
}