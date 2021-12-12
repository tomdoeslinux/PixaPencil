package com.realtomjoney.pyxlmoose.extensions

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

const val snackbarBackgroundTint: String = "#eaddff"

enum class SnackbarDuration(val timeValue: Int) {
    DEFAULT(1500), MEDIUM(3000), LONG(6000)
}

fun View.showSnackbar(snackbarText: String, duration: SnackbarDuration) {
    Snackbar.make(this, snackbarText, duration.timeValue)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor(snackbarBackgroundTint))
            .show()
}