
package com.therealbluepandabear.pixapencil.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.Display
import android.view.inputmethod.InputMethodManager
import java.util.*

fun Activity.getScreenOrientation(): Int {
    val getOrient: Display = windowManager.defaultDisplay

    val orientation: Int = if (getOrient.width < getOrient.height) {
            Configuration.ORIENTATION_PORTRAIT
        } else {
            Configuration.ORIENTATION_LANDSCAPE
        }

    return orientation
}

fun Activity.hideKeyboard() {
    try {
        val inputMethodManager =
            (Objects.requireNonNull(this).getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager)
        inputMethodManager.hideSoftInputFromWindow(
            this.currentFocus!!.windowToken, 0
        )
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}