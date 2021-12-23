package com.realtomjoney.pyxlmoose.listeners

import com.google.android.material.textfield.TextInputEditText

// ✔ CLEAN

interface NewCanvasFragmentListener {
    fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textFieldTwo: TextInputEditText)
}