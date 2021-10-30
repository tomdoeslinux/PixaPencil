package com.realtomjoney.pyxlmoose

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

const val snackbarBackgroundTint: String = "#eaddff"

enum class SnackbarDuration(val timeValue: Int) {
    DEFAULT(1500),
}

fun View.showSnackbar(snackbarText: String, duration: SnackbarDuration) {
    Snackbar.make(this, snackbarText, duration.timeValue)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor(snackbarBackgroundTint))
            .show()
}

fun View.showSnackbarWithAction(snackbarText: String, duration: SnackbarDuration, actionButtonText: String, action: View.OnClickListener) {
    Snackbar.make(this, snackbarText, duration.timeValue)
        .setTextColor(Color.BLACK)
        .setBackgroundTint(Color.parseColor(snackbarBackgroundTint))
        .setAction(actionButtonText, action)
        .show()
}