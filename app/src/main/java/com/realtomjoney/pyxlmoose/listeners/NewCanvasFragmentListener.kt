package com.realtomjoney.pyxlmoose.listeners

import android.view.View
import com.google.android.material.textfield.TextInputEditText

interface NewCanvasFragmentListener {
    fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText)
}