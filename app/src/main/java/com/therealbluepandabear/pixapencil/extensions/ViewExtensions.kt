package com.therealbluepandabear.pixapencil.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.setOnLongPressListener(event: (it: View) -> Unit) {
    setOnLongClickListener {
        event(it)
        true
    }
}

fun View.hideSoftInput() {
    clearFocus()
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}