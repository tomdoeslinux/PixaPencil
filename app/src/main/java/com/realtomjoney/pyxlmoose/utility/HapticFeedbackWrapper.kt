package com.realtomjoney.pyxlmoose.utility

import android.view.HapticFeedbackConstants
import android.view.View

object HapticFeedbackWrapper {
    fun performHapticFeedbackOn(view: View) {
        view.performHapticFeedback(
            HapticFeedbackConstants.VIRTUAL_KEY,
            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        )
    }
}