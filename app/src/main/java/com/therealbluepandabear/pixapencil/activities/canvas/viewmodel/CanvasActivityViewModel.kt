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

package com.therealbluepandabear.pixapencil.activities.canvas.viewmodel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.database.DitherBrushDatabase
import com.therealbluepandabear.pixapencil.enums.FlipValue
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.DitherBrush

class CanvasActivityViewModel : ViewModel() {
    var undoStack = ArrayDeque<BitmapAction>(listOf())
    var redoStack = ArrayDeque<BitmapAction>(listOf())

    var currentBitmapAction: BitmapAction? = null

    var primaryColor: Int = Color.BLACK
    var secondaryColor: Int = Color.BLUE

    var isPrimaryColorSelected: Boolean = true

    var currentDitherBrush: DitherBrush = DitherBrushDatabase.toList().first()
    var currentBrush: Brush = BrushesDatabase.toList().first()
    var currentSymmetryMode: SymmetryMode = SymmetryMode.None
    var currentTool: Tool = Tool.PencilTool
    var currentRotation: Float = 0f
    var trueRotation: Float = 0f
    var flipMatrix = mutableListOf<FlipValue>()

    var saved = true
    var unsavedChangesDialogShown = false

    var currentBitmap: Bitmap? = null
}