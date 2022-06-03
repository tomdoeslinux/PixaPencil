package com.therealbluepandabear.pixapencil.activities.canvas.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.therealbluepandabear.pixapencil.models.BitmapAction

class CanvasActivityViewModel : ViewModel() {
    var bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var undoStack: MutableList<BitmapAction> = mutableListOf()

    var currentBitmapAction: BitmapAction? = null

    var createdYet: Boolean = false

    var primaryColor: Int = Color.BLACK
    var secondaryColor: Int = Color.BLUE
}