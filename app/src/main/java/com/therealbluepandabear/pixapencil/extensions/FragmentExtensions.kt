package com.therealbluepandabear.pixapencil.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.util.*

fun Fragment.hideKeyboard() {
    try {
        val inputMethodManager =
            (Objects.requireNonNull(requireActivity()).getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager)
        inputMethodManager.hideSoftInputFromWindow(
            this.requireActivity().currentFocus!!.windowToken, 0
        )
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}