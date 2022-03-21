
package com.realtomjoney.pyxlmoose.extensions

import android.app.Activity
import android.content.res.Configuration
import android.view.Display

fun Activity.getScreenOrientation(): Int {
    val getOrient: Display = windowManager.defaultDisplay

    val orientation: Int = if (getOrient.width < getOrient.height) {
            Configuration.ORIENTATION_PORTRAIT
        } else {
            Configuration.ORIENTATION_LANDSCAPE
        }

    return orientation
}