package com.therealbluepandabear.pixapencil.activities.canvas.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.database.DitherBrushDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.DitherBrush

class CanvasActivityViewModel : ViewModel() {
    var bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var undoStack: MutableList<BitmapAction> = mutableListOf()

    var currentBitmapAction: BitmapAction? = null

    var createdYet: Boolean = false

    var primaryColor: Int = Color.BLACK
    var secondaryColor: Int = Color.BLUE

    var currentDitherBrush: DitherBrush = DitherBrushDatabase.toList().first()
    var currentBrush: Brush = BrushesDatabase.toList().first()
    var currentSymmetryMode: SymmetryMode = SymmetryMode.defaultSymmetryMode
}