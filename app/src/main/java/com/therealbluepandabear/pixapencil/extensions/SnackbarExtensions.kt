/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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