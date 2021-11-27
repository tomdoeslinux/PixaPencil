package com.realtomjoney.pyxlmoose.listeners

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface NewCanvasFragmentListener {
    fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textInputLayout: TextInputLayout)
}