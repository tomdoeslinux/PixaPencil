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
    var currentSymmetryMode: SymmetryMode = SymmetryMode.defaultSymmetryMode
    var currentTool: Tool = Tool.defaultTool
    var currentRotation: Float = 0f
    var flipMatrix = mutableListOf<FlipValue>()

    var saved = true
    var unsavedChangesDialogShown = false

    var currentBitmap: Bitmap? = null
}