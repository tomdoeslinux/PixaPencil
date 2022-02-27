package com.realtomjoney.pyxlmoose.listeners

import android.graphics.Bitmap

interface FindAndReplaceFragmentListener {
    fun onColorToFindTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap
    fun onColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap
    fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?)
}
