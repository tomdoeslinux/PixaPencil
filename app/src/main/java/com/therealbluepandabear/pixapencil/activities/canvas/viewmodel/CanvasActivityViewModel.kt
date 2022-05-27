package com.therealbluepandabear.pixapencil.activities.canvas.viewmodel

import androidx.lifecycle.ViewModel
import com.therealbluepandabear.pixapencil.models.BitmapAction

class CanvasActivityViewModel : ViewModel() {
    var bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var undoStack: MutableList<BitmapAction> = mutableListOf()

    var currentBitmapAction: BitmapAction? = null

    var createdYet: Boolean = false
}