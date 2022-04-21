package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun View.showSnackbar(snackbarText: String, duration: SnackbarDuration) {
    Snackbar.make(this, snackbarText, duration.timeValue)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor(StringConstants.Colors.SnackbarBackgroundColor))
            .show()
}

fun View.showSnackbarWithAction(snackbarText: String, duration: SnackbarDuration, actionText: String, actionOnClickListener: View.OnClickListener) {
    Snackbar.make(this, snackbarText, duration.timeValue)
        .setTextColor(Color.BLACK)
        .setBackgroundTint(Color.parseColor(StringConstants.Colors.SnackbarBackgroundColor))
        .setAction(actionText, actionOnClickListener)
        .show()
}