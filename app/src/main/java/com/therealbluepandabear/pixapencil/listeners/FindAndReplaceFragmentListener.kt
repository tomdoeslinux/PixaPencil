package com.therealbluepandabear.pixapencil.listeners

import android.graphics.Bitmap

interface FindAndReplaceFragmentListener {
    fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?)
}
