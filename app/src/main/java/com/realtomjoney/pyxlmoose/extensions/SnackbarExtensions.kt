package com.realtomjoney.pyxlmoose.extensions

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.realtomjoney.pyxlmoose.enums.SnackbarDuration
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun View.showSnackbar(snackbarText: String, duration: SnackbarDuration) {
    Snackbar.make(this, snackbarText, duration.timeValue)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor(StringConstants.SnackbarBackgroundColor))
            .show()
}

fun View.showSnackbarWithAction(snackbarText: String, duration: SnackbarDuration, actionText: String, actionOnClickListener: View.OnClickListener) {
    Snackbar.make(this, snackbarText, duration.timeValue)
        .setTextColor(Color.BLACK)
        .setBackgroundTint(Color.parseColor(StringConstants.SnackbarBackgroundColor))
        .setAction(actionText, actionOnClickListener)
        .show()
}