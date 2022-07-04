package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration

fun View.showSnackbar(snackbarText: String, duration: SnackbarDuration) {
    Snackbar.make(this, snackbarText, duration.timeValue)
        .setTextColor(Color.BLACK)
        .setBackgroundTint(ContextCompat.getColor(context, R.color.snackbarBackgroundColor))
        .setAnimationMode(ANIMATION_MODE_SLIDE)
        .show()
}

fun View.showSnackbarWithAction(snackbarText: String, duration: SnackbarDuration, actionText: String, actionOnClickListener: View.OnClickListener) {
    Snackbar.make(this, snackbarText, duration.timeValue)
        .setTextColor(Color.BLACK)
        .setBackgroundTint(ContextCompat.getColor(context, R.color.snackbarBackgroundColor))
        .setAction(actionText, actionOnClickListener)
        .setAnimationMode(ANIMATION_MODE_SLIDE)
        .show()
}

fun View.showSnackbarWithActionAndCallback(
    snackbarText: String,
    duration: SnackbarDuration,
    actionText: String,
    actionOnClickListener: View.OnClickListener,
    onDismissedCallback: () -> Unit) {
    Snackbar.make(this, snackbarText, duration.timeValue)
        .setTextColor(Color.BLACK)
        .setBackgroundTint(ContextCompat.getColor(context, R.color.snackbarBackgroundColor))
        .setAction(actionText, actionOnClickListener)
        .setAnimationMode(ANIMATION_MODE_SLIDE)
        .addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)

                if (event == DISMISS_EVENT_SWIPE) {
                    onDismissedCallback()
                }
            }
        })
        .show()
}